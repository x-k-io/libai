package com.kite.libai.provider.account.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum AccountStatus {
    /**
     * AccountStatus
     */
    NORMAL("normal", "正常"),
    LOCKED("locked", "锁定"),
    FORBIDDEN("forbidden", "禁言");

    private final String status;
    private final String desc;
}
