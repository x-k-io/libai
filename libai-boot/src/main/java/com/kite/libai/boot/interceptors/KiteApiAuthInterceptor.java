package com.kite.libai.boot.interceptors;

import com.kite.libai.common.constant.KiteHeader;
import com.kite.libai.common.context.RequestContext;
import com.kite.libai.common.context.RequestContextHolder;
import com.kite.libai.common.exception.AuthenticationException;
import com.kite.libai.common.model.KiteAccount;
import com.kite.libai.common.result.KiteSecurityCode;
import com.kite.libai.common.utils.NumberUtils;
import com.kite.libai.boot.annotation.KitePermission;
import com.kite.libai.boot.service.KiteSecurityService;
import lombok.AllArgsConstructor;
import org.springframework.lang.NonNull;
import org.springframework.stereotype.Component;
import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.AsyncHandlerInterceptor;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@Component
@AllArgsConstructor
public class KiteApiAuthInterceptor implements AsyncHandlerInterceptor {

    private final KiteSecurityService kiteSecurityService;

    @Override
    public boolean preHandle(@NonNull HttpServletRequest request, @NonNull HttpServletResponse response, @NonNull Object handler) {
        // 非控制器请求直接跳出
        if (!(handler instanceof HandlerMethod)) {
            return true;
        }
        RequestContext requestContext = buildRequestContext(request);
        RequestContextHolder.set(requestContext);
        HandlerMethod handlerMethod = (HandlerMethod) handler;
        KitePermission kitePermission = handlerMethod.getMethod().getAnnotation(KitePermission.class);
        if (kitePermission == null) {
            return true;
        }
        // 查询用户信息 判断登录是否有效
        KiteAccount kiteAccount = kiteSecurityService.getKiteAccount();
        if (kiteAccount == null) {
            throw new AuthenticationException(KiteSecurityCode.TOKEN_IS_INVALID);
        }
        requestContext.setAccount(kiteAccount);
        requestContext.setAccountId(kiteAccount.getId());
        return true;
    }

    private RequestContext buildRequestContext(HttpServletRequest request) {
        RequestContext requestContext = new RequestContext();
        requestContext.setToken(request.getHeader(KiteHeader.X_TOKEN));
        requestContext.setDeviceId(request.getHeader(KiteHeader.X_DEVICE_ID));
        requestContext.setNonce(request.getHeader(KiteHeader.X_NONCE));
        requestContext.setTimestamp(NumberUtils.toLong(request.getHeader(KiteHeader.X_TIMESTAMP)));
        requestContext.setSign(request.getHeader(KiteHeader.X_SIGN));
        requestContext.setTraceId(request.getHeader(KiteHeader.X_TRACE_ID));
        requestContext.setTimeZone(request.getHeader(KiteHeader.X_TIMEZONE));
        requestContext.setLocale(request.getLocale());
        return requestContext;
    }
}
