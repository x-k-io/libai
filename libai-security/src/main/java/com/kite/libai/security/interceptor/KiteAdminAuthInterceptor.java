package com.kite.libai.security.interceptor;

import com.kite.libai.core.constant.KiteHeader;
import com.kite.libai.core.context.RequestContext;
import com.kite.libai.core.context.RequestContextHolder;
import com.kite.libai.core.exception.ServiceException;
import com.kite.libai.core.model.KiteAccount;
import com.kite.libai.core.result.KiteSecurityCode;
import com.kite.libai.core.utils.NumberUtils;
import com.kite.libai.security.annotation.KitePermission;
import com.kite.libai.security.service.KiteSecurityService;
import lombok.AllArgsConstructor;
import org.apache.commons.collections4.CollectionUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.lang.NonNull;
import org.springframework.stereotype.Component;
import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.AsyncHandlerInterceptor;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@Component
@AllArgsConstructor
public class KiteAdminAuthInterceptor implements AsyncHandlerInterceptor {

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
        KiteAccount kiteAccount = kiteSecurityService.getKiteUser();
        if (kiteAccount == null) {
            throw new ServiceException(KiteSecurityCode.TOKEN_IS_INVALID);
        }
        requestContext.setAccount(kiteAccount);
        if (StringUtils.isNotBlank(kitePermission.value())) {
            if (CollectionUtils.isEmpty(kiteAccount.getPermissions()) || !kiteAccount.getPermissions().contains(kitePermission.value())) {
                throw new ServiceException(KiteSecurityCode.AUTH_IS_FORBIDDEN);
            }
        }
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
