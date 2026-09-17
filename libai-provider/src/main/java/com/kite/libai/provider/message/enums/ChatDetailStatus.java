package com.kite.libai.provider.message.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum ChatDetailStatus {

    /**
     * ChatDetailStatus
     */
    VISIBLE("visible", "可见"),
    INVISIBLE("invisible", "不可见");

    private final String status;
    private final String desc;
}
