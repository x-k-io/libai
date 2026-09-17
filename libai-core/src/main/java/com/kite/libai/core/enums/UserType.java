package com.kite.libai.core.enums;


import org.apache.commons.lang3.StringUtils;
import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum UserType {

    ACCOUNT("account", "a"),
    USER("user", "u");

    private final String type;

    private final String prefix;

    public static UserType get(String token) {
        if (StringUtils.isNotBlank(token) && token.startsWith(ACCOUNT.type)) {
            return ACCOUNT;
        }
        return USER;
    }
}
