package com.kite.libai.provider.account.utils;

import com.kite.libai.common.exception.ServiceException;
import com.kite.libai.common.result.KiteSecurityCode;
import com.kite.libai.provider.account.model.entity.AccountRefreshToken;
import com.kite.libai.provider.account.model.response.AccountTokenVo;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.ExpiredJwtException;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.security.Keys;

import javax.crypto.SecretKey;
import java.util.Base64;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

public class AccountJwtUtil {

    public static final String SECRET_STR = "abc1234567890abc1234567890abc1234567890abc1234567890";

    public static final Long ACCESS_TOKEN_EXPIRE = 5 * 60 * 1000L;

    private static final SecretKey secretKey;

    static {
        byte[] bytes = Base64.getDecoder().decode(SECRET_STR);
        secretKey = Keys.hmacShaKeyFor(bytes);
    }

    public static AccountTokenVo generateToken(AccountRefreshToken accountRefreshToken) {
        AccountTokenVo accountToken = new AccountTokenVo();
        String accessToken = generateAccessToken(accountRefreshToken.getAccountId(), accountRefreshToken.getDeviceId());
        accountToken.setAccessToken(accessToken);
        accountToken.setAccessExpire(System.currentTimeMillis() + AccountJwtUtil.ACCESS_TOKEN_EXPIRE);
        accountToken.setRefreshToken(accountRefreshToken.getRefreshToken());
        return accountToken;
    }

    /**
     * 生成Access Token
     */
    private static String generateAccessToken(Long accountId, String deviceId) {
        Map<String, Object> claims = new HashMap<>();
        claims.put("accountId", accountId);
        claims.put("deviceId", deviceId);

        Date now = new Date();
        Date expireDate = new Date(now.getTime() + ACCESS_TOKEN_EXPIRE);

        return Jwts.builder()
                .claims(claims)
                .issuedAt(now)
                .expiration(expireDate)
                .signWith(secretKey)
                .compact();
    }

    /**
     * 解析token，获取载荷
     */
    public static Claims getClaimsByToken(String token) {
        try {
            return Jwts.parser()
                    .verifyWith(secretKey)
                    .build()
                    .parseSignedClaims(token)
                    .getPayload();
        } catch (ExpiredJwtException e) {
            // token过期异常
            throw new ServiceException(KiteSecurityCode.TOKEN_IS_INVALID);
        } catch (Exception e) {
            throw new ServiceException(KiteSecurityCode.TOKEN_IS_INVALID);
        }
    }


    public static Long getAccountId(String token) {
        Claims claims = getClaimsByToken(token);
        return claims.get("accountId", Long.class);
    }

    /**
     * 校验token是否过期
     */
    public static boolean isTokenExpired(String token) {
        Claims claims = getClaimsByToken(token);
        return claims.getExpiration().before(new Date());
    }
}
