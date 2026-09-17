package com.kite.libai.provider.account.service;

import com.kite.libai.boot.cache.CacheKey;
import com.kite.libai.boot.cache.CacheKeys;
import com.kite.libai.boot.cache.LibaiRedisTemplate;
import com.kite.libai.boot.spring.SpringContextUtils;
import com.kite.libai.core.context.RequestContextUtils;
import com.kite.libai.core.exception.ServiceException;
import com.kite.libai.core.result.SystemCode;
import com.kite.libai.core.utils.RandomType;
import com.kite.libai.core.utils.RandomUtils;
import com.kite.libai.provider.account.enums.AccountExceptionCode;
import com.kite.libai.provider.account.enums.VerifyCodeType;
import com.kite.libai.provider.account.model.entity.Account;
import com.kite.libai.provider.account.model.request.LoginSendCodeRequest;
import com.kite.libai.provider.account.model.request.NonLoginSendCodeRequest;
import com.kite.libai.provider.account.model.request.SendCodeRequest;
import com.kite.libai.provider.tripartite.service.AliSendMessageService;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;

@Slf4j
@Service
@AllArgsConstructor
public class VerifyCodeService {

    private static final String DEFAULT_SMS_CODE = "999999";
    private static final Long VERIFY_CODE_EXPIRE = 5 * 60L;

    private final LibaiRedisTemplate libaiRedisTemplate;

    private final AliSendMessageService aliSendMessageService;

    private final AccountService accountService;

    public Boolean nonLoginSendCode(NonLoginSendCodeRequest request) {
        SendCodeRequest sendCodeRequest = new SendCodeRequest();
        sendCodeRequest.setType(request.getType());
        sendCodeRequest.setMobile(request.getMobile());
        sendCodeRequest.setDeviceId(RequestContextUtils.getDeviceId());
        sendCodeRequest.setIp(RequestContextUtils.getIp());
        return sendMessage(sendCodeRequest);
    }

    public Boolean loginSendCode(LoginSendCodeRequest request) {
        Long accountId = RequestContextUtils.getAccountId();
        Account account = accountService.getById(accountId);
        if (account == null) {
            throw new ServiceException(AccountExceptionCode.KITE_USER_NO_EXIT);
        }
        SendCodeRequest sendCodeRequest = new SendCodeRequest();
        sendCodeRequest.setType(request.getType());
        sendCodeRequest.setMobile(account.getMobile());
        sendCodeRequest.setDeviceId(RequestContextUtils.getDeviceId());
        sendCodeRequest.setIp(RequestContextUtils.getIp());
        return sendMessage(sendCodeRequest);
    }

    private Boolean sendMessage(SendCodeRequest sendCodeRequest) {
        VerifyCodeType verifyCodeType = VerifyCodeType.getByType(sendCodeRequest.getType());
        if (verifyCodeType == null) {
            throw new ServiceException(SystemCode.FAILURE);
        }
        String mobile = sendCodeRequest.getMobile();
        String code = RandomUtils.random(6, RandomType.INT);
        log.info("手机号：{}, 验证码： {}", mobile, code);
        Map<String, String> templateParam = new HashMap<>(16);
        templateParam.put("code", code);
        CacheKey cacheKey = CacheKeys.SMS_CODE_KEY.get(verifyCodeType.getType(), sendCodeRequest.getMobile());
        libaiRedisTemplate.setEx(cacheKey.getKey(), code, VERIFY_CODE_EXPIRE);
        if (SpringContextUtils.isProd()) {
            return aliSendMessageService.sendMessage(mobile, verifyCodeType.getTemplateCode(), templateParam);
        } else {
            return Boolean.TRUE;
        }
    }

    public void checkCode(VerifyCodeType verifyCodeType, String mobile, String code) {
        CacheKey cacheKey = CacheKeys.SMS_CODE_KEY.get(verifyCodeType.getType(), mobile);
        String cacheCode = libaiRedisTemplate.get(cacheKey, String.class);
        if (StringUtils.isBlank(cacheCode)) {
            throw new ServiceException(AccountExceptionCode.KITE_CODE_INVALID_ERROR);
        }
        if (cacheCode.equals(code)) {
            libaiRedisTemplate.delete(cacheKey.getKey());
            return;
        }
        if (!SpringContextUtils.isProd() && DEFAULT_SMS_CODE.equalsIgnoreCase(code)) {
            libaiRedisTemplate.delete(cacheKey.getKey());
            return;
        }
        throw new ServiceException(AccountExceptionCode.KITE_CODE_INVALID_ERROR);
    }
}
