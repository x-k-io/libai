package com.kite.libai.boot.enums;

import com.kite.libai.boot.spring.SpringContextUtils;
import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum KiteEnv {
    DEV("dev"),
    TEST("test"),
    PROD("prod");
    private final String env;

    public static boolean isdev() {
        return KiteEnv.DEV.equals(SpringContextUtils.getContext().getEnvironment().getActiveProfiles());
    }
}
