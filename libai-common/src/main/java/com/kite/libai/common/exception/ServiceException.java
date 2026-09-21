package com.kite.libai.common.exception;

import com.kite.libai.common.result.IResultCode;
import com.kite.libai.common.result.SystemCode;
import com.kite.libai.common.result.Result;
import lombok.Getter;

@Getter
public class ServiceException extends RuntimeException {

    private final Result<?> result;

    public ServiceException(Result<?> result) {
        super(result.getMessage());
        this.result = result;
    }

    public ServiceException(IResultCode rCode) {
        this(rCode, rCode.getMessage());
    }

    public ServiceException(String message) {
        super(message);
        this.result = Result.fail(SystemCode.SERVICE_ERROR, message);
    }

    public ServiceException(IResultCode rCode, String message) {
        super(message);
        this.result = Result.fail(rCode, message);
    }

    public ServiceException(Throwable cause) {
        this(cause.getMessage(), cause);
    }

    public ServiceException(String message, Throwable cause) {
        super(message, cause);
        doFillInStackTrace();
        this.result = null;
    }


    @SuppressWarnings("unchecked")
    public <T> Result<T> getResult() {
        return (Result<T>) result;
    }

    /**
     * 提高性能
     *
     * @return Throwable
     */
    @Override
    public Throwable fillInStackTrace() {
        return this;
    }

    public Throwable doFillInStackTrace() {
        return super.fillInStackTrace();
    }

}
