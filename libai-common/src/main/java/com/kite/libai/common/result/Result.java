package com.kite.libai.common.result;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;


import java.io.Serializable;

@Data
@ToString
@NoArgsConstructor
public class Result<T> implements Serializable {

    private int code;

    private String message;

    private T data;

    private Pagination pagination;

    @Data
    @NoArgsConstructor
    @AllArgsConstructor
    private static class Pagination implements Serializable {

        private Long pageCount;

        private Long total;
    }

    private Result(IResultCode resultCode) {
        this(resultCode, resultCode.getMessage(), null);
    }

    private Result(IResultCode resultCode, String msg) {
        this(resultCode, msg, null);
    }

    private Result(IResultCode resultCode, T data) {
        this(resultCode, resultCode.getMessage(), data);
    }

    private Result(IResultCode resultCode, String msg, T data) {
        this.code = resultCode.getCode();
        this.message = msg;
        this.data = data;
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
    public static <T> Result<T> success(T data) {
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
    public static <T> Result<T> success(T data, Long pageCount, Long total) {
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
}
