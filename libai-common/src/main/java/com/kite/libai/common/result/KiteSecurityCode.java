package com.kite.libai.common.result;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum KiteSecurityCode implements IResultCode {

    /**
     * token失效
     */
    TOKEN_IS_INVALID(200101, "登录失效，请重新登录!"),

    /**
     * 账号冻结失效
     */
    TOKEN_IS_FROZEN_INVALID(200103, "您的账号被冻结，请联系客服!"),

    /**
     * 您的账号无操作权限
     */
    AUTH_IS_FORBIDDEN(200199, "您暂无此操作权限，请联系客服!");

    /**
     * code编码
     */
    final int code;
    /**
     * 中文信息描述
     */
    final String msg;
}
