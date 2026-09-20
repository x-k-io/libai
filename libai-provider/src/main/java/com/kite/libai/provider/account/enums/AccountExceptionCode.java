package com.kite.libai.provider.account.enums;

import com.kite.libai.common.result.IResultCode;
import com.kite.libai.common.result.SystemCode;
import lombok.Getter;

@Getter
public enum AccountExceptionCode implements IResultCode {
    /**
     * 返回code码
     */
    KITE_USER_EXIT("用户已存在"),
    KITE_USER_NO_EXIT("未查询到用户"),
    KITE_USER_NOT_ACTIVE("用户未激活"),
    KITE_USER_IS_LOCK("用户已锁定"),
    KITE_USER_NOT_AUTH("用户未认证"),
    KITE_PASSWORD_FORMAT_ERROR("密码格式不正确"),
    KITE_PASSWORD_ERROR("手机号或密码错误"),
    KITE_PASSWORD_IDENTICAL("新旧密码相同"),
    KITE_OLD_PASSWORD_ERROR("原密码不正确"),
    KITE_CODE_INVALID_ERROR("验证码错误"),
    KITE_LOGIN_CHANNEL_ERROR("无效的登录渠道"),
    KITE_LOGIN_TYPE_ERROR("无效的登录类型"),
    KITE_BIND_MOBILE_TIMEOUT("绑定手机号超时，请重新绑定"),
    KITE_BIND_MOBILE_ERROR("新手机号与原手机号一致，无需重复修改"),
    KITE_BIND_MOBILE_EXIT("该手机号已被其他账号绑定，无法换绑"),
    ;

    final int code;

    final String msg;

    AccountExceptionCode(final int code, final String msg) {
        this.code = code;
        this.msg = msg;
    }

    AccountExceptionCode(final String msg) {
        this.code = SystemCode.SERVICE_ERROR_CODE;
        this.msg = msg;
    }
}
