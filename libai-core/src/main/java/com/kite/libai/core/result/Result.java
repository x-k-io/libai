package com.kite.libai.core.result;

import com.kite.libai.core.exception.ServiceException;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;


import java.io.Serializable;
import java.util.Optional;

@Data
@ToString
@NoArgsConstructor
public class Result<T> implements Serializable {

    private int code;

    private String msg;

    private T data;

    private Pagination pagination;

    @Data
    @NoArgsConstructor
    @AllArgsConstructor
    private static class Pagination implements Serializable {

        private static final long serialVersionUID = -4348183755776153974L;
        private Long pageCount;

        private Long total;
    }

    private Result(IResultCode resultCode) {
        this(resultCode, resultCode.getMsg(), null);
    }

    private Result(IResultCode resultCode, String msg) {
        this(resultCode, msg, null);
    }

    private Result(IResultCode resultCode, T data) {
        this(resultCode, resultCode.getMsg(), data);
    }

    private Result(IResultCode resultCode, String msg, T data) {
        this.code = resultCode.getCode();
        this.msg = msg;
        this.data = data;
    }

    /**
     * 判断返回是否为成功
     *
     * @return 是否成功
     */
    public boolean isSuccess() {
        return isSuccess(this);
    }

    /**
     * 判断返回是否为成功
     *
     * @param result Result
     * @return 是否成功
     */
    public static boolean isSuccess( Result<?> result) {
        return Optional.ofNullable(result)
                .map(r -> r.code)
                .map(code -> SystemCode.SUCCESS.code == code)
                .orElse(Boolean.FALSE);
    }

    /**
     * 判断返回是否为成功
     *
     * @param result Result
     * @return 是否成功
     */
    public static boolean isNotSuccess( Result<?> result) {
        return !Result.isSuccess(result);
    }

    /**
     * 获取data
     *
     * @param result Result
     * @param <T>    泛型标记
     * @return 泛型对象
     */
    
    public static <T> T getData( Result<T> result) {
        return Optional.ofNullable(result)
                .filter(r -> r.isSuccess())
                .map(x -> x.data)
                .orElse(null);
    }

    /**
     * 返回成功
     *
     * @param <T> 泛型标记
     * @return Result
     */
    public static <T> Result<T> success() {
        return new Result<>(SystemCode.SUCCESS);
    }

    /**
     * 成功-携带数据
     *
     * @param data 数据
     * @param <T>  泛型标记
     * @return Result
     */
    public static <T> Result<T> success( T data) {
        return new Result<>(SystemCode.SUCCESS, data);
    }

    /**
     * 分页查询成功-携带数据返回
     *
     * @param data      数据
     * @param <T>       泛型标记
     * @param pageCount 总页数
     * @param total     总条数
     * @return Result
     */
    public static <T> Result<T> success( T data, Long pageCount, Long total) {
        Pagination pagination = new Pagination(pageCount, total);
        Result<T> result = new Result<>(SystemCode.SUCCESS, data);
        result.setPagination(pagination);
        return result;
    }

    /**
     * 根据状态返回成功或者失败
     *
     * @param status 状态
     * @param msg    异常msg
     * @param <T>    泛型标记
     * @return Result
     */
    public static <T> Result<T> status(boolean status, String msg) {
        return status ? Result.success() : Result.fail(msg);
    }

    /**
     * 根据状态返回成功或者失败
     *
     * @param status 状态
     * @param sCode  异常code码
     * @param <T>    泛型标记
     * @return Result
     */
    public static <T> Result<T> status(boolean status, IResultCode sCode) {
        return status ? Result.success() : Result.fail(sCode);
    }

    /**
     * 返回失败信息，用于 web
     *
     * @param msg 失败信息
     * @param <T> 泛型标记
     * @return {Result}
     */
    public static <T> Result<T> fail(String msg) {
        return new Result<>(SystemCode.FAILURE, msg);
    }

    /**
     * 返回失败信息
     *
     * @param rCode 异常枚举
     * @param <T>   泛型标记
     * @return {Result}
     */
    public static <T> Result<T> fail(IResultCode rCode) {
        return new Result<>(rCode);
    }

    /**
     * 返回失败信息
     *
     * @param rCode 异常枚举
     * @param msg   失败信息
     * @param <T>   泛型标记
     * @return {Result}
     */
    public static <T> Result<T> fail(IResultCode rCode, String msg) {
        return new Result<>(rCode, msg);
    }

    /**
     * 当 result 不成功时：直接抛出失败异常，返回传入的 result。
     *
     * @param result R
     */
    public static void throwOnFail(Result<?> result) {
        if (Result.isNotSuccess(result)) {
            throw new ServiceException(result);
        }
    }

    /**
     * 当 result 不成功时：直接抛出失败异常，返回传入的 rCode
     *
     * @param result R
     * @param rCode  异常枚举
     */
    public static void throwOnFail(Result<?> result, IResultCode rCode) {
        if (Result.isNotSuccess(result)) {
            throw new ServiceException(rCode);
        }
    }

    /**
     * 当 result 不成功时：直接抛出失败异常，返回传入的 rCode、message
     *
     * @param result R
     * @param rCode  异常枚举
     * @param msg    失败信息
     */
    public static void throwOnFail(Result<?> result, IResultCode rCode, String msg) {
        if (Result.isNotSuccess(result)) {
            throw new ServiceException(rCode, msg);
        }
    }

    /**
     * 当 status 不为 true 时：直接抛出失败异常 rCode
     *
     * @param status status
     * @param rCode  异常枚举
     */
    public static void throwOnFalse(boolean status, IResultCode rCode) {
        if (!status) {
            throw new ServiceException(rCode);
        }
    }

    /**
     * 当 status 不为 true 时：直接抛出失败异常 rCode、message
     *
     * @param status status
     * @param rCode  异常枚举
     * @param msg    失败信息
     */
    public static void throwOnFalse(boolean status, IResultCode rCode, String msg) {
        if (!status) {
            throw new ServiceException(rCode, msg);
        }
    }

    /**
     * 直接抛出失败异常，抛出 code 码
     *
     * @param rCode IResultCode
     */
    public static void throwFail(IResultCode rCode) {
        throw new ServiceException(rCode);
    }

    /**
     * 直接抛出失败异常，抛出 code 码
     *
     * @param rCode   IResultCode
     * @param message 自定义消息
     */
    public static void throwFail(IResultCode rCode, String message) {
        throw new ServiceException(rCode, message);
    }
}
