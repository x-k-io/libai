package com.kite.libai.provider.throne.service;

import java.time.LocalDateTime;
import java.util.List;

import com.kite.libai.common.context.RequestContextUtils;
import com.kite.libai.common.exception.AuthenticationException;
import com.kite.libai.common.result.KiteSecurityCode;
import com.kite.libai.common.utils.RandomUtils;
import com.kite.libai.provider.throne.model.entity.UserRefreshToken;
import com.kite.libai.provider.throne.repository.UserRefreshTokenRepository;
import org.springframework.stereotype.Service;


import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
@AllArgsConstructor
public class UserRefreshTokenService {

    private final UserRefreshTokenRepository userRefreshTokenRepository;

    public UserRefreshToken createRefreshToken(Long userId) {
        UserRefreshToken userRefreshToken = new UserRefreshToken();
        userRefreshToken.setUserId(userId);
        userRefreshToken.setRefreshToken(RandomUtils.getUUID());
        userRefreshToken.setDeviceId(RequestContextUtils.getDeviceId());
        userRefreshToken.setIp(RequestContextUtils.getIp());
        userRefreshToken.setExpiresTime(LocalDateTime.now().plusHours(3));
        userRefreshToken.setLoginTime(LocalDateTime.now());
        userRefreshToken.setUpdateTime(LocalDateTime.now());
        userRefreshTokenRepository.insert(userRefreshToken);
        return userRefreshToken;
    }


    public UserRefreshToken refreshAccessToken(String refreshToken) {
        UserRefreshToken userRefreshToken = userRefreshTokenRepository.getByRefreshToken(refreshToken);
        if (userRefreshToken == null || LocalDateTime.now().isAfter(userRefreshToken.getExpiresTime())) {
            throw new AuthenticationException(KiteSecurityCode.TOKEN_IS_INVALID);
        }
        userRefreshToken.setRefreshToken(RandomUtils.getUUID());
        userRefreshToken.setExpiresTime(LocalDateTime.now().plusHours(3));
        userRefreshToken.setUpdateTime(LocalDateTime.now());
        userRefreshTokenRepository.updateById(userRefreshToken);
        return userRefreshToken;
    }

    public void excludeRefreshToken(String refreshTokenStr) {
        UserRefreshToken refreshToken = userRefreshTokenRepository.getByRefreshToken(refreshTokenStr);
        if (refreshToken == null) {
            return;
        }
        userRefreshTokenRepository.deleteById(refreshToken.getId());
    }

    public void clearSessionByUserId(Long userId) {
        List<UserRefreshToken> userRefreshTokens = userRefreshTokenRepository.getByUserId(userId);
        for (UserRefreshToken userRefreshToken : userRefreshTokens) {
            userRefreshTokenRepository.deleteById(userRefreshToken.getId());
        }
    }
}
