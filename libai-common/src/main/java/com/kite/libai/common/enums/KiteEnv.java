package com.kite.libai.common.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum KiteEnv {
    DEV("dev"),
    TEST("test"),
    PROD("prod");
    private final String env;
}
