package com.kite.libai.boot.response;

import com.kite.libai.common.exception.AccessDeniedException;
import com.kite.libai.common.exception.AuthenticationException;
import com.kite.libai.common.exception.ServiceException;
import com.kite.libai.common.result.Result;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.servlet.NoHandlerFoundException;

@Slf4j
@RestControllerAdvice
public class GlobalExceptionHandler {


    /**
     * 未登录
     */
    @ExceptionHandler(AuthenticationException.class)
    @ResponseStatus(HttpStatus.UNAUTHORIZED)
    public Result<String> handleAuthenticationException(AuthenticationException e) {
        return e.getResult();
    }

    /**
     * 无访问权限
     */
    @ExceptionHandler(AccessDeniedException.class)
    @ResponseStatus(HttpStatus.FORBIDDEN)
    public Result<String> handleAccessDeniedException(AccessDeniedException e) {
        return e.getResult();
    }

    /**
     * 自定义业务异常捕获
     */
    @ExceptionHandler(ServiceException.class)
    public Result<String> handleBusinessException(ServiceException e) {
        log.error("业务异常：", e);
        return e.getResult();
    }

    /**
     * 404 异常捕获
     */
    @ExceptionHandler(NoHandlerFoundException.class)
    @ResponseStatus(HttpStatus.NOT_FOUND)
    public Result<String> handleNoHandlerFound(NoHandlerFoundException e) {
        return Result.fail("接口不存在");
    }

    /**
     * 全局异常捕获
     */
    @ExceptionHandler(Exception.class)
    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    public Result<String> handleException(Exception e) {
        log.error("系统异常：", e);
        return Result.fail(e.getMessage());
    }
}
