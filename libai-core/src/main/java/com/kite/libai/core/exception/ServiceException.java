package com.kite.libai.core.exception;

import com.kite.libai.core.result.IResultCode;
import com.kite.libai.core.result.Result;
import com.kite.libai.core.result.SystemCode;
import lombok.Getter;

@Getter
public class ServiceException extends RuntimeException {

    private static final long serialVersionUID = 2359767895161832954L;


    private final Result<?> result;

    public ServiceException(Result<?> result) {
        super(result.getMsg());
        this.result = result;
    }

    public ServiceException(IResultCode rCode) {
        this(rCode, rCode.getMsg());
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
