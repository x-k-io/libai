package com.kite.libai.provider.account.enums;

import com.kite.libai.common.result.IResultCode;
import com.kite.libai.common.result.SystemCode;
import lombok.Getter;

@Getter
public enum SecurityExceptionCode implements IResultCode {
    KITE_CODE_INVALID_ERROR("验证码错误"),
    KITE_LOGIN_CHANNEL_ERROR("无效的登录渠道"),
    KITE_LOGIN_TYPE_ERROR("无效的登录类型"),
    ;

    final int code;

    final String msg;

    SecurityExceptionCode(final int code, final String msg) {
        this.code = code;
        this.msg = msg;
    }

    SecurityExceptionCode(final String msg) {
        this.code = SystemCode.SERVICE_ERROR_CODE;
        this.msg = msg;
    }
}
