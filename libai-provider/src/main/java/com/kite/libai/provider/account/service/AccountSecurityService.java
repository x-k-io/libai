package com.kite.libai.provider.account.service;

import com.kite.libai.core.cache.CacheKeys;
import com.kite.libai.core.cache.LibaiRedisTemplate;
import com.kite.libai.common.context.RequestContextUtils;
import com.kite.libai.common.enums.UserType;
import com.kite.libai.common.exception.ServiceException;
import com.kite.libai.common.model.KiteAccount;
import com.kite.libai.common.utils.DesensitizationUtils;
import com.kite.libai.provider.account.enums.AccountExceptionCode;
import com.kite.libai.provider.account.enums.AccountStatus;
import com.kite.libai.provider.account.enums.AccountTypes;
import com.kite.libai.provider.account.enums.LoginChannel;
import com.kite.libai.provider.account.enums.LoginType;
import com.kite.libai.provider.account.enums.VerifyCodeType;
import com.kite.libai.provider.account.model.entity.Account;
import com.kite.libai.provider.account.model.entity.AccountLoginLog;
import com.kite.libai.provider.account.model.entity.AccountRefreshToken;
import com.kite.libai.provider.account.model.request.AccountLogoutRequest;
import com.kite.libai.provider.account.model.request.CreateAccountRequest;
import com.kite.libai.provider.account.model.request.LoginRequest;
import com.kite.libai.provider.account.model.request.RefreshAccessTokenRequest;
import com.kite.libai.provider.account.model.response.AccountTokenVo;
import com.kite.libai.provider.account.repository.AccountLoginLogRepository;
import com.kite.libai.provider.account.utils.AccountJwtUtil;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class AccountSecurityService {

    private final AccountService accountService;

    private final VerifyCodeService verifyCodeService;

    private final LibaiRedisTemplate libaiRedisTemplate;

    private final AccountRefreshTokenService accountTokenService;

    private final AccountLoginLogRepository loginLogRepository;


    /**
     * 登录
     *
     * @param loginRequest 登录信息
     * @return Token
     */
    public AccountTokenVo login(LoginRequest loginRequest) {
        LoginChannel loginChannel = LoginChannel.getByChannel(loginRequest.getChannel());
        LoginType loginType = LoginType.getByType(loginRequest.getType());
        if (loginChannel == null || loginType == null) {
            throw new ServiceException(AccountExceptionCode.KITE_USER_NO_EXIT);
        }
        Account account = null;
        // 短信验证码登录
        if (LoginType.MESSAGE_LOGIN.equals(loginType)) {
            String loginName = loginRequest.getLoginName();
            verifyCodeService.checkCode(VerifyCodeType.LOGIN, loginName, loginRequest.getCode());
            account = accountService.getByMobile(loginName);
            if (null == account) {
                CreateAccountRequest request = new CreateAccountRequest();
                request.setType(AccountTypes.PERSONAL.getType());
                request.setMobile(loginName);
                account = accountService.create(request);
            }
        }
        // 授权登录
        if (LoginType.OAUTH_LOGIN.equals(loginType)) {
            Long accountId = libaiRedisTemplate.get(CacheKeys.OAUTH_LOGIN_CODE.get(loginRequest.getCode()), Long.class);
            account = accountService.getById(accountId);
            if (null == account) {
                throw new ServiceException(AccountExceptionCode.KITE_USER_NO_EXIT);
            }
        }
        // 账号密码
        if (LoginType.PWD_LOGIN.equals(loginType)) {
            String loginName = loginRequest.getLoginName();
            account = accountService.getByMobile(loginName);
            if (null == account) {
                throw new ServiceException(AccountExceptionCode.KITE_USER_NO_EXIT);
            }
            boolean matches = accountService.matches(loginRequest.getPassword(), account.getPassword());
            if (!matches) {
                throw new ServiceException(AccountExceptionCode.KITE_PASSWORD_ERROR);
            }
        }
        // 登录类型不支持
        if (account == null) {
            throw new ServiceException(AccountExceptionCode.KITE_USER_NO_EXIT);
        }
        // 账号被锁定
        if (AccountStatus.LOCKED.getStatus().equals(account.getStatus())) {
            throw new ServiceException(AccountExceptionCode.KITE_USER_IS_LOCK);
        }
        // 创建token
        AccountRefreshToken accountRefreshToken = accountTokenService.createRefreshToken(account.getId());
        AccountTokenVo accountToken = AccountJwtUtil.generateToken(accountRefreshToken);
        savaLoginLog(accountRefreshToken, loginType, loginChannel);
        return accountToken;
    }

    public AccountTokenVo refreshAccessToken(RefreshAccessTokenRequest request) {
        AccountRefreshToken refreshToken = accountTokenService.refreshAccessToken(request.getRefreshToken());
        return AccountJwtUtil.generateToken(refreshToken);
    }

    public void logout(AccountLogoutRequest request) {
        accountTokenService.excludeRefreshToken(request.getRefreshToken());
    }

    public KiteAccount getKiteAccount() {
        String token = RequestContextUtils.getToken();
        Long accountId = AccountJwtUtil.getAccountId(token);
        Account account = accountService.getById(accountId);
        if (account != null) {
            KiteAccount kiteAccount = new KiteAccount();
            kiteAccount.setId(account.getId());
            kiteAccount.setType(UserType.ACCOUNT.getType());
            kiteAccount.setNickname(account.getNickname());
            kiteAccount.setName(account.getNickname());
            kiteAccount.setMobile(DesensitizationUtils.mobileNo(account.getMobile()));
            kiteAccount.setAvatar(account.getAvatar());
            return kiteAccount;
        }
        return null;
    }

    private void savaLoginLog(AccountRefreshToken accountRefreshToken, LoginType loginType, LoginChannel loginChannel) {
        AccountLoginLog accountLoginLog = new AccountLoginLog();
        accountLoginLog.setAccountId(accountRefreshToken.getAccountId());
        accountLoginLog.setLoginType(loginType.getType());
        accountLoginLog.setLoginChannel(loginChannel.getChannel());
        accountLoginLog.setAppName(RequestContextUtils.getAppName());
        accountLoginLog.setAppVersion(RequestContextUtils.getAppVersion());
        accountLoginLog.setDeviceId(accountRefreshToken.getDeviceId());
        accountLoginLog.setIp(RequestContextUtils.getIp());
        accountLoginLog.setUserAgent(RequestContextUtils.getUserAgent());
        accountLoginLog.setLoginTime(accountRefreshToken.getLoginTime());
        accountLoginLog.setCreateTime(accountLoginLog.getLoginTime());
        loginLogRepository.save(accountLoginLog);
    }
}
