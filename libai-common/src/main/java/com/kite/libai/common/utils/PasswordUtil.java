package com.kite.libai.common.utils;

import at.favre.lib.crypto.bcrypt.BCrypt;

public final class PasswordUtil {
    // cost因子，推荐10，和Spring BCryptPasswordEncoder默认一致
    private static final int COST = 10;

    private PasswordUtil() {
    }

    /**
     * 加密，输出格式和Spring BCrypt完全一模一样，老数据库密码直接兼容
     */
    public static String encode(String rawPassword) {
        if (rawPassword == null || rawPassword.isBlank()) {
            throw new IllegalArgumentException("password cannot be blank");
        }
        return BCrypt.withDefaults().hashToString(COST, rawPassword.toCharArray());
    }

    /**
     * 校验密码
     */
    public static boolean matches(String rawPassword, String encodedHash) {
        if (rawPassword == null || encodedHash == null || encodedHash.isBlank()) {
            return false;
        }
        BCrypt.Result result = BCrypt.verifyer().verify(rawPassword.toCharArray(), encodedHash);
        return result.verified;
    }
}
