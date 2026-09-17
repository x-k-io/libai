package com.kite.libai.provider.common.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum IdBizType {
    ORDER_NO("order_no"),
    USER_NO("user_no"),
    UNIQUE_ID("unique_id");
    private final String bizType;
}
