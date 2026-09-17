package com.kite.libai.provider.account.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum LoginType {
    /**
     * LoginType
     */
    PWD_LOGIN("pwd_login", "密码登录"),
    MESSAGE_LOGIN("message_login", "短信登录"),
    OAUTH_LOGIN("oauth_login", "授权登录"),
    SCAN_LOGIN("scan_login", "扫码登录"),
    QUICK_LOGIN("quick_login", "一键登录");

    private final String type;
    private final String desc;


    public static LoginType getByType(String type) {
        for (LoginType loginType : LoginType.values()) {
            if (loginType.type.equals(type)) {
                return loginType;
            }
        }
        return null;
    }
}
