package com.kite.libai.boot.response;

import com.kite.libai.common.exception.ServiceException;
import com.kite.libai.common.result.Result;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@Slf4j
@RestControllerAdvice
public class GlobalExceptionHandler {

    /**
     * 全局异常捕获
     */
    @ExceptionHandler(Exception.class)
    public Result<String> handleException(Exception e) {
        log.error("系统异常：", e);
        return Result.fail(e.getMessage());
    }

    /**
     * 自定义业务异常捕获
     */
    @ExceptionHandler(ServiceException.class)
    public Result<String> handleBusinessException(ServiceException e) {
        return e.getResult();
    }
}
