package com.kite.libai.provider.account.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum AccountTypes {

    PERSONAL("personal", "个人"),
    INSTITUTION("institution", "机构"),
    OFFICIAL("official", "官方"),
    ROBOT("robot", "机器人");

    private final String type;
    private final String desc;
}
