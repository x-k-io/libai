package com.kite.libai.provider.account.service;

import com.kite.libai.common.context.RequestContextUtils;
import com.kite.libai.common.exception.ServiceException;
import com.kite.libai.common.result.KiteSecurityCode;
import com.kite.libai.common.utils.RandomUtils;
import com.kite.libai.provider.account.model.entity.AccountRefreshToken;
import com.kite.libai.provider.account.repository.AccountRefreshTokenRepository;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

@Slf4j
@Service
@AllArgsConstructor
public class AccountRefreshTokenService {

    private final AccountRefreshTokenRepository accountRefreshTokenRepository;

    public AccountRefreshToken createRefreshToken(Long accountId) {
        AccountRefreshToken accountRefreshToken = new AccountRefreshToken();
        accountRefreshToken.setAccountId(accountId);
        accountRefreshToken.setRefreshToken(RandomUtils.getUUID());
        accountRefreshToken.setDeviceId(RequestContextUtils.getDeviceId());
        accountRefreshToken.setIp(RequestContextUtils.getIp());
        accountRefreshToken.setExpiresTime(LocalDateTime.now().plusDays(90));
        accountRefreshToken.setLoginTime(LocalDateTime.now());
        accountRefreshToken.setUpdateTime(LocalDateTime.now());
        accountRefreshTokenRepository.save(accountRefreshToken);
        return accountRefreshToken;
    }


    public AccountRefreshToken refreshAccessToken(String refreshToken) {
        AccountRefreshToken accountRefreshToken = accountRefreshTokenRepository.getByRefreshToken(refreshToken);
        if (accountRefreshToken == null || LocalDateTime.now().isAfter(accountRefreshToken.getExpiresTime())) {
            throw new ServiceException(KiteSecurityCode.TOKEN_IS_INVALID);
        }
        accountRefreshToken.setRefreshToken(RandomUtils.getUUID());
        accountRefreshToken.setExpiresTime(LocalDateTime.now().plusDays(90));
        accountRefreshToken.setUpdateTime(LocalDateTime.now());
        accountRefreshTokenRepository.updateById(accountRefreshToken);
        return accountRefreshToken;
    }

    public void excludeRefreshToken(String refreshTokenStr) {
        AccountRefreshToken refreshToken = accountRefreshTokenRepository.getByRefreshToken(refreshTokenStr);
        if (refreshToken == null) {
            return;
        }
        accountRefreshTokenRepository.deleteById(refreshToken.getId());
    }
}
