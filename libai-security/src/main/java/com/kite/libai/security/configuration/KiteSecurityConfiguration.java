package com.kite.libai.security.configuration;

import com.kite.libai.security.interceptor.KiteAdminAuthInterceptor;
import com.kite.libai.security.interceptor.KiteApiAuthInterceptor;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import lombok.AllArgsConstructor;

import java.util.Arrays;
import java.util.List;

@Configuration
@AllArgsConstructor
public class KiteSecurityConfiguration implements WebMvcConfigurer {

    private static final List<String> EXCLUDE_PATTERNS = Arrays.asList(
            "/error",
            "/null/**",
            "/actuator/**",
            "/prometheus/**");

    private final KiteApiAuthInterceptor kiteApiAuthInterceptor;
    private final KiteAdminAuthInterceptor kiteAdminAuthInterceptor;

    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        // request拦截器
        registry.addInterceptor(kiteApiAuthInterceptor)
                .addPathPatterns("/api/app/**")
                .excludePathPatterns(EXCLUDE_PATTERNS);
        registry.addInterceptor(kiteAdminAuthInterceptor)
                .addPathPatterns("/api/admin/**")
                .excludePathPatterns(EXCLUDE_PATTERNS);
    }
}

