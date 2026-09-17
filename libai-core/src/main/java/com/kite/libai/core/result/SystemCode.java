package com.kite.libai.core.result;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum SystemCode implements IResultCode {

    /**
     * 系统未知异常
     */
    FAILURE(SystemCode.FAILURE_CODE, "服务器开小差了，请稍后重试"),
    /**
     * 操作成功
     */
    SUCCESS(SystemCode.SUCCESS_CODE, "请求成功"),
    /**
     * 缺少必要的请求参数
     */
    PARAM_MISS(SystemCode.PARAM_MISS_CODE, "缺少必要的请求参数"),
    /**
     * 请求参数类型错误
     */
    PARAM_TYPE_ERROR(SystemCode.PARAM_TYPE_ERROR_CODE, "请求参数类型错误"),
    /**
     * 请求参数绑定错误
     */
    PARAM_BIND_ERROR(SystemCode.PARAM_BIND_ERROR_CODE, "请求参数绑定错误"),
    /**
     * 参数校验失败
     */
    PARAM_VALID_ERROR(SystemCode.PARAM_VALID_ERROR_CODE, "参数校验失败"),
    /**
     * 404 没找到请求
     */
    NOT_FOUND(SystemCode.NOT_FOUND_CODE, "404 没找到请求"),
    /**
     * 消息不能读取
     */
    MSG_NOT_READABLE(SystemCode.MSG_NOT_READABLE_CODE, "请求信息解析失败"),
    /**
     * 不支持当前请求方法
     */
    METHOD_NOT_SUPPORTED(SystemCode.METHOD_NOT_SUPPORTED_CODE, "不支持当前请求方法"),
    /**
     * 不支持当前媒体类型
     */
    MEDIA_TYPE_NOT_SUPPORTED(SystemCode.MEDIA_TYPE_NOT_SUPPORTED_CODE, "不支持当前媒体类型"),
    /**
     * 不接受的媒体类型
     */
    MEDIA_TYPE_NOT_ACCEPT(SystemCode.MEDIA_TYPE_NOT_ACCEPT_CODE, "不接受的媒体类型"),
    /**
     * 请求被拒绝
     */
    REQ_REJECT(SystemCode.REQ_REJECT_CODE, "非法的请求"),

    // -------------------------------------------------------------//
    /**
     * 业务层通用异常
     */
    SERVICE_ERROR(SystemCode.SERVICE_ERROR_CODE, "业务异常");

    /**
     * 通用 异常 code
     */
    public static final int FAILURE_CODE = -1;
    public static final int SUCCESS_CODE = 1;
    public static final int PARAM_MISS_CODE = 100000;
    public static final int PARAM_TYPE_ERROR_CODE = 100001;
    public static final int PARAM_BIND_ERROR_CODE = 100002;
    public static final int PARAM_VALID_ERROR_CODE = 100003;
    public static final int NOT_FOUND_CODE = 100004;
    public static final int MSG_NOT_READABLE_CODE = 100005;
    public static final int METHOD_NOT_SUPPORTED_CODE = 100006;
    public static final int MEDIA_TYPE_NOT_SUPPORTED_CODE = 100007;
    public static final int MEDIA_TYPE_NOT_ACCEPT_CODE = 100008;
    public static final int REQ_REJECT_CODE = 100009;

    /**
     * 通用业务层异常 code
     */
    public static final int SERVICE_ERROR_CODE = 200000;

    /**
     * code编码
     */
    final int code;
    /**
     * 中文信息描述
     */
    final String msg;
}
