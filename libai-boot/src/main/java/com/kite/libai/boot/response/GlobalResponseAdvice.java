package com.kite.libai.boot.response;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.kite.libai.boot.annotation.IgnoreResponseAdvice;
import com.kite.libai.common.result.PageResult;
import com.kite.libai.common.result.Result;
import lombok.AllArgsConstructor;
import org.springframework.core.MethodParameter;
import org.springframework.http.MediaType;
import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.http.server.ServerHttpRequest;
import org.springframework.http.server.ServerHttpResponse;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.servlet.mvc.method.annotation.ResponseBodyAdvice;

@AllArgsConstructor
@RestControllerAdvice
public class GlobalResponseAdvice implements ResponseBodyAdvice<Object> {

    private ObjectMapper objectMapper;

    @Override
    public boolean supports(MethodParameter returnType, Class<? extends HttpMessageConverter<?>> converterType) {
        return !returnType.hasMethodAnnotation(IgnoreResponseAdvice.class);
    }

    @Override
    public Object beforeBodyWrite(Object body, MethodParameter returnType, MediaType selectedContentType,
                                  Class<? extends HttpMessageConverter<?>> selectedConverterType,
                                  ServerHttpRequest request, ServerHttpResponse response) {
        String path = request.getURI().getPath();
        if (path.startsWith("/actuator")) {
            return body;
        }
        if (body instanceof Result<?>) {
            return body;
        }
        // PageResult 分页对象，组装Result
        if (body instanceof PageResult<?> pageResult) {
            return Result.success(pageResult.getRecords(), pageResult.getPageCount(), pageResult.getTotal());
        }
        if (body instanceof String) {
            try {
                return objectMapper.writeValueAsString(Result.success(body));
            } catch (Exception e) {
                return Result.fail("返回值封装异常");
            }
        }
        return Result.success(body);
    }
}
