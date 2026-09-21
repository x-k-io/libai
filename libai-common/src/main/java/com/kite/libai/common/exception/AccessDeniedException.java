package com.kite.libai.common.exception;

import com.kite.libai.common.result.IResultCode;
import com.kite.libai.common.result.Result;

public class AccessDeniedException extends RuntimeException {
    private final Result<?> result;

    public AccessDeniedException(IResultCode rCode) {
        super(rCode.getMessage());
        this.result = Result.fail(rCode);
    }

    @SuppressWarnings("unchecked")
    public <T> Result<T> getResult() {
        return (Result<T>) result;
    }
}
