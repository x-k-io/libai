package com.kite.libai.provider.account.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum VerifyCodeType {

    LOGIN("login", "SMS_212080072"),
    MODIFY_PASSWORD("modify_password", "SMS_212080072"),
    MODIFY_MOBILE("modify_mobile", "SMS_212080072"),
    BIND_MOBILE("bind_mobile", "SMS_212080072"),
    REFUND("refund", "SMS_212080073");

    /**
     * 类型
     */
    private final String type;


    private final String templateCode;


    public static VerifyCodeType getByType(String key) {
        VerifyCodeType[] verifyCodeTypes = VerifyCodeType.values();
        for (VerifyCodeType verifyCodeType : verifyCodeTypes) {
            if (verifyCodeType.type.equals(key)) {
                return verifyCodeType;
            }
        }
        return null;
    }
}
