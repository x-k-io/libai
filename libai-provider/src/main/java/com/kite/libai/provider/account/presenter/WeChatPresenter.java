package com.kite.libai.provider.account.presenter;

import com.kite.libai.core.cache.LibaiRedisTemplate;
import com.kite.libai.common.utils.RandomUtils;
import com.kite.libai.provider.account.model.entity.Account;
import com.kite.libai.provider.account.model.entity.TripartiteAccount;
import com.kite.libai.provider.account.model.entity.TripartiteApp;
import com.kite.libai.provider.account.model.request.AppOauthRequest;
import com.kite.libai.provider.account.model.request.CreateAccountRequest;
import com.kite.libai.provider.account.model.request.ExchangeBindRequest;
import com.kite.libai.provider.account.model.request.WeChatAuthRequest;
import com.kite.libai.provider.account.model.request.WeChatBindRequest;
import com.kite.libai.provider.account.model.response.AppOauthResponse;
import com.kite.libai.provider.account.model.response.ExchangeBindResponse;
import com.kite.libai.provider.account.service.TripartiteAccountService;
import com.kite.libai.provider.account.service.TripartiteAppService;
import com.kite.libai.provider.tripartite.feign.WeChatRemoteService;
import com.kite.libai.provider.tripartite.feign.model.WeChatInfo;
import org.springframework.stereotype.Component;

import com.kite.libai.common.result.Result;
import com.kite.libai.common.utils.JsonUtils;

import org.apache.commons.lang3.StringUtils;
import com.kite.libai.core.cache.CacheKeys;
import com.kite.libai.provider.account.enums.AccountExceptionCode;
import com.kite.libai.provider.account.enums.AccountTypes;
import com.kite.libai.provider.account.enums.Gender;
import com.kite.libai.provider.account.service.AccountService;
import com.kite.libai.provider.account.utils.MiniProgramUtil;
import com.kite.libai.provider.account.utils.PinyinUtils;
import com.kite.libai.provider.tripartite.feign.constant.WeChatStatusCode;
import com.kite.libai.provider.tripartite.feign.model.WeChatMiniProgramSessionKey;
import com.kite.libai.provider.tripartite.feign.model.WeChatPhone;

import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Component
@AllArgsConstructor
public class WeChatPresenter {
    private final AccountService accountService;

    private final TripartiteAppService tripartiteAppService;

    private final TripartiteAccountService tripartiteAccountService;

    private final WeChatRemoteService weChatRemoteService;

    private final LibaiRedisTemplate libaiRedisTemplate;

    public Result<AppOauthResponse> miniOauth(AppOauthRequest appOauthRequest) {

        TripartiteApp tripartiteApp = tripartiteAppService.getByAppCode(appOauthRequest.getAppCode());
        if (null == tripartiteApp) {
            return Result.fail("应用不存在");
        }
        String res = weChatRemoteService.code2session(tripartiteApp.getAppId(), tripartiteApp.getAppSecret(),
                appOauthRequest.getCode(),
                "authorization_code");
        WeChatMiniProgramSessionKey sessionKey = JsonUtils.parse(res, WeChatMiniProgramSessionKey.class);
        log.info("WeChatMiniProgramController miniOauth===> code:{}, sessionKey:{}", appOauthRequest.getCode(),
                sessionKey);
        int errCode = sessionKey.getErrcode();
        if (WeChatStatusCode.CODE_INVALID_CODE == errCode) {
            return Result.fail("微信code无效");
        }
        if (WeChatStatusCode.CODE_BEEN_USED_CODE == errCode) {
            return Result.fail("微信code已被使用");
        }
        TripartiteAccount tripartiteAccount =
                tripartiteAccountService.getTripartiteAccount(tripartiteApp.getAppId(), sessionKey.getOpenId(), null,
                        null);
        if (null == tripartiteAccount) {
            // 直接生成获取用户信息ST
            String sessionSt = RandomUtils.getUUID();
            libaiRedisTemplate.set(CacheKeys.OAUTH_SESSION_KEY.get(sessionSt), sessionKey);
            AppOauthResponse appOauthVo = new AppOauthResponse();
            appOauthVo.setSessionSt(sessionSt);
            return Result.success(appOauthVo);
        }
        if (null != tripartiteAccount.getAccountId()) {
            // 直接生成登录code
            String code = RandomUtils.getUUID();
            libaiRedisTemplate.set(CacheKeys.OAUTH_LOGIN_CODE.get(code), tripartiteAccount.getAccountId());
            AppOauthResponse appOauthVo = new AppOauthResponse();
            appOauthVo.setCode(code);
            return Result.success(appOauthVo);
        } else {
            // 直接生成绑定ST
            String bindSt = RandomUtils.getUUID();
            sessionKey.setUnionId(tripartiteAccount.getUnionId());
            libaiRedisTemplate.set(CacheKeys.OAUTH_SESSION_KEY.get(bindSt), sessionKey);
            AppOauthResponse appOauthVo = new AppOauthResponse();
            appOauthVo.setBindSt(bindSt);
            return Result.success(appOauthVo);
        }
    }

    public Result<AppOauthResponse> miniProgramAuth(WeChatAuthRequest weChatAuthRequest) {
        TripartiteApp tripartiteApp = tripartiteAppService.getByAppCode(weChatAuthRequest.getAppCode());
        if (null == tripartiteApp) {
            return Result.fail("应用不存在");
        }
        String st = weChatAuthRequest.getSessionSt();
        // 1.从缓存取对应的sessionKey`和unionId
        WeChatMiniProgramSessionKey sessionKeyVo =
                libaiRedisTemplate.get(CacheKeys.OAUTH_SESSION_KEY.get(st), WeChatMiniProgramSessionKey.class);
        log.info("2.WeChatMiniProgramController miniProgramAuth==> sessionKeyVo:{}", sessionKeyVo);
        if (null == sessionKeyVo) {
            log.warn("2.[通过小程序绑定微信],绑定St:{}对应sessionKey和unionId不存在", st);
            return Result.fail("微信凭证不存在");
        }
        // 2.解密用户信息
        String sessionKey = sessionKeyVo.getSessionKey();
        String encryptedData = weChatAuthRequest.getEncryptedData();
        String iv = weChatAuthRequest.getIv();
        WeChatInfo weChatInfo = MiniProgramUtil.decryptWeChatInfo(encryptedData, sessionKey, iv);
        log.info("2.WeChatMiniProgramController miniProgramAuth==> 解密结果：weChatInfo:{}", weChatInfo);
        if (null == weChatInfo) {
            log.warn("2.[通过小程序绑定微信],encryptedData:{},sessionKey:{},iv:{}解密失败", encryptedData, sessionKey, iv);
            return Result.fail("微信凭证不存在");
        }
        if (tripartiteApp.getIsPlatform() && StringUtils.isBlank(weChatInfo.getUnionId())) {
            log.warn("2.[通过小程序绑定微信],encryptedData:{},sessionKey:{},iv:{}解密失败", encryptedData, sessionKey, iv);
            return Result.fail("微信凭证不存在");
        }
        TripartiteAccount tripartiteUser = new TripartiteAccount();
        tripartiteUser.setType(tripartiteApp.getType());
        tripartiteUser.setAppCode(tripartiteApp.getAppCode());
        tripartiteUser.setAppId(tripartiteApp.getAppId());
        tripartiteUser.setOpenId(sessionKeyVo.getOpenId());
        tripartiteUser.setUnionId(sessionKeyVo.getUnionId());
        tripartiteUser.setNickname(weChatAuthRequest.getUserInfo().getNickName());
        tripartiteUser.setAvatar(weChatAuthRequest.getUserInfo().getAvatarUrl());
        tripartiteUser.setGender(Gender.getGender(weChatInfo.getGender()));
        tripartiteUser.setCountry(weChatInfo.getCountry());
        tripartiteUser.setProvince(weChatInfo.getProvince());
        tripartiteUser.setCity(weChatInfo.getCity());
        log.info("2.WeChatMiniProgramController 创建tripartiteUser ==> tripartiteUser:{}", tripartiteUser);
        tripartiteAccountService.save(tripartiteUser);
        // 直接生成绑定ST
        String bindSt = RandomUtils.getUUID();
        sessionKeyVo.setUnionId(weChatInfo.getUnionId());
        libaiRedisTemplate.set(CacheKeys.OAUTH_SESSION_KEY.get(bindSt), sessionKeyVo);
        AppOauthResponse appOauthVo = new AppOauthResponse();
        appOauthVo.setBindSt(bindSt);
        return Result.success(appOauthVo);
    }

    public Result<ExchangeBindResponse> bind(WeChatBindRequest weChatBindRequest) {
        TripartiteApp tripartiteApp = tripartiteAppService.getByAppCode(weChatBindRequest.getAppCode());
        if (null == tripartiteApp) {
            return Result.fail("应用不存在");
        }
        String bindSt = weChatBindRequest.getBindWeChatSt();
        String encryptedData = weChatBindRequest.getEncryptedData();
        String iv = weChatBindRequest.getIv();
        String mobile = null;
        String loginName = weChatBindRequest.getLoginName();
        String smsCode = weChatBindRequest.getSmsCode();
        boolean isMobileWay = StringUtils.isNotBlank(loginName);
        boolean isWeChatMobileWay = StringUtils.isNotBlank(encryptedData) && StringUtils.isNotBlank(iv);
        if (!isWeChatMobileWay && !isMobileWay) {
            log.warn("[通过小程序绑定微信],是否微信手机号方式:{},是否手机号登录方式:{}", isWeChatMobileWay, isMobileWay);
            return Result.fail("缺少微信加密数据或者登录名或验证码");
        }
        // 2.从缓存取对应的sessionKey和unionId
        WeChatMiniProgramSessionKey sessionKeyVo =
                libaiRedisTemplate.get(CacheKeys.OAUTH_SESSION_KEY.get(bindSt), WeChatMiniProgramSessionKey.class);
        log.info("3.WeChatMiniProgramController bind==> sessionKeyVo:{}", sessionKeyVo);
        if (null == sessionKeyVo) {
            log.warn("3.[通过小程序绑定微信],绑定St:{}对应sessionKey和unionId不存在", bindSt);
            return Result.fail("微信凭证不存在");
        }
        // 3.如果是微信手机号登录方式 需要解密拿到手机号
        if (isWeChatMobileWay) {
            String sessionKey = sessionKeyVo.getSessionKey();
            WeChatPhone weChatPhone = MiniProgramUtil.decryptWeChatPhone(encryptedData, sessionKey, iv);
            if (null == weChatPhone || StringUtils.isBlank(weChatPhone.getPhoneNumber())) {
                log.warn("3.[通过小程序绑定微信],encryptedData:{},sessionKey:{},iv:{}解密失败", encryptedData, sessionKey, iv);
                return Result.fail("微信凭证不存在");
            }
            mobile = weChatPhone.getPhoneNumber();
            sessionKeyVo.setMobile(mobile);
        }
        // 暂时不校验账号信息
        String openId = sessionKeyVo.getOpenId();
        String unionId = sessionKeyVo.getUnionId();
        log.info("3.[通过小程序绑定微信],绑定St:{},loginName:{},mobile:{},openId:{},unionId:{} 开始根据手机号绑定微信 ", bindSt, loginName,
                mobile, openId, unionId);
        TripartiteAccount bind = null;
        if (tripartiteApp.getIsPlatform()) {
            bind = tripartiteAccountService.getTripartiteAccount(tripartiteApp.getAppId(), null, unionId, null);
        } else {
            bind = tripartiteAccountService.getTripartiteAccount(tripartiteApp.getAppId(), openId, null, null);
        }
        if (null == bind) {
            return Result.fail("微信凭证不存在");
        }
        Account account = null;
        if (isWeChatMobileWay) {
            account = accountService.getByMobile(mobile);
            if (null == account) {
                CreateAccountRequest request = new CreateAccountRequest();
                request.setType(AccountTypes.PERSONAL.getType());
                request.setMobile(mobile);
                request.setNickname(bind.getNickname());
                account = accountService.create(request);
            }
        } else {
            account = accountService.getByMobile(mobile);
            if (null == account) {
                return Result.fail(AccountExceptionCode.KITE_USER_NO_EXIT);
            }
        }
        TripartiteAccount tripartiteUser =
                tripartiteAccountService.getTripartiteAccount(tripartiteApp.getAppId(), null, null, account.getId());
        if (null == tripartiteUser) {
            bind.setAccountId(account.getId());
            bind.setMobile(account.getMobile());
            if (StringUtils.isBlank(account.getAvatar())) {
                account.setAvatar(bind.getAvatar());
            }
            if (StringUtils.isBlank(account.getNickname())) {
                account.setNickname(bind.getNickname());
                account.setInitials(PinyinUtils.getFirstLetter(account.getNickname()));
            }
            if (StringUtils.isBlank(account.getCountry())) {
                account.setCountry(bind.getCountry());
            }
            if (StringUtils.isBlank(account.getProvince())) {
                account.setProvince(bind.getProvince());
            }
            if (StringUtils.isBlank(account.getCountry())) {
                account.setCity(bind.getCity());
            }
            accountService.updateById(account);
            tripartiteAccountService.updateById(bind);
            // 直接生成登录code
            String code = RandomUtils.getUUID();
            libaiRedisTemplate.set(CacheKeys.OAUTH_LOGIN_CODE.get(code), bind.getAccountId());
            ExchangeBindResponse exchangeBindVo = new ExchangeBindResponse();
            exchangeBindVo.setCode(code);
            return Result.success(exchangeBindVo);
        } else {
            // 直接生成换绑ST
            String exchangeSt = RandomUtils.getUUID();
            libaiRedisTemplate.set(CacheKeys.OAUTH_SESSION_KEY.get(exchangeSt), sessionKeyVo);
            ExchangeBindResponse exchangeBindVo = new ExchangeBindResponse();
            exchangeBindVo.setExchangeSt(exchangeSt);
            return Result.success(exchangeBindVo);
        }
    }

    public Result<ExchangeBindResponse> exchangeBindMiniProgram(ExchangeBindRequest exchangeBindRequest) {

        TripartiteApp tripartiteApp = tripartiteAppService.getByAppCode(exchangeBindRequest.getAppCode());
        if (null == tripartiteApp) {
            return Result.fail("应用不存在");
        }
        String exchangeSt = exchangeBindRequest.getExchangeSt();
        Boolean sure = exchangeBindRequest.getSure();
        // 取消换绑
        if (!sure) {
            log.info("[换绑微信]exchangeSt:{}取消换绑", exchangeSt);
            libaiRedisTemplate.delete(CacheKeys.OAUTH_SESSION_KEY.getKey(exchangeSt));
            return Result.success();
        }
        // 2.验证st
        WeChatMiniProgramSessionKey sessionKeyVo =
                libaiRedisTemplate.get(CacheKeys.OAUTH_SESSION_KEY.get(exchangeSt), WeChatMiniProgramSessionKey.class);
        if (null == sessionKeyVo) {
            log.warn("[换绑微信]exchangeSt:{}不存在", exchangeSt);
            return Result.fail("换绑ST失效");
        }
        String mobile = sessionKeyVo.getMobile();
        Account account = accountService.getByMobile(mobile);
        TripartiteAccount tripartiteUser =
                tripartiteAccountService.getTripartiteAccount(tripartiteApp.getAppId(), null, null, account.getId());
        if (null != tripartiteUser) {
            tripartiteAccountService.deleteById(tripartiteUser.getId());
        }
        TripartiteAccount bind = tripartiteAccountService.getTripartiteAccount(tripartiteApp.getAppId(), null,
                sessionKeyVo.getUnionId(), null);
        if (null == bind) {
            return Result.fail("微信凭证不存在");
        }
        bind.setAccountId(account.getId());
        tripartiteAccountService.updateById(bind);
        // 直接生成登录code
        String code = RandomUtils.getUUID();
        libaiRedisTemplate.set(CacheKeys.OAUTH_LOGIN_CODE.get(code), bind.getAccountId());
        ExchangeBindResponse exchangeBindVo = new ExchangeBindResponse();
        exchangeBindVo.setCode(code);
        return Result.success(exchangeBindVo);
    }
}
