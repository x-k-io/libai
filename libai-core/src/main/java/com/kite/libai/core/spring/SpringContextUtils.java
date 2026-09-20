package com.kite.libai.core.spring;

import com.kite.libai.common.enums.KiteEnv;
import org.springframework.aop.framework.AopContext;
import org.springframework.beans.BeansException;
import org.springframework.beans.factory.ObjectProvider;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.context.ApplicationEvent;
import org.springframework.core.ResolvableType;
import org.springframework.lang.Nullable;
import org.springframework.stereotype.Component;

@Component
@SuppressWarnings("unchecked")
public class SpringContextUtils implements ApplicationContextAware {
    @Nullable
    private static ApplicationContext context;
    @Nullable
    private static String applicationName;
    @Nullable
    private static String port;

    @Override
    public void setApplicationContext(ApplicationContext context) throws BeansException {
        SpringContextUtils.context = context;
        SpringContextUtils.applicationName = context.getId();
        SpringContextUtils.port = context.getEnvironment().getProperty("server.port");
    }

    @Nullable
    public static <T> T getBean(Class<T> clazz) {
        if (context == null) {
            return null;
        }
        return context.getBean(clazz);
    }

    @Nullable
    public static <T> T getBean(String beanName) {
        if (context == null) {
            return null;
        }
        return (T) context.getBean(beanName);
    }

    @Nullable
    public static <T> T getBean(String beanName, Class<T> clazz) {
        if (context == null) {
            return null;
        }
        return context.getBean(beanName, clazz);
    }

    @Nullable
    public static <T> ObjectProvider<T> getBeanProvider(Class<T> clazz) {
        if (context == null) {
            return null;
        }
        return context.getBeanProvider(clazz);
    }

    @Nullable
    public static <T> ObjectProvider<T> getBeanProvider(ResolvableType resolvableType) {
        if (context == null) {
            return null;
        }
        return context.getBeanProvider(resolvableType);
    }

    @Nullable
    public static ApplicationContext getContext() {
        return context;
    }

    @Nullable
    public static String getPort() {
        return port;
    }

    @Nullable
    public static String getApplicationName() {
        return applicationName;
    }

    public static void publishEvent(ApplicationEvent event) {
        if (context == null) {
            return;
        }
        context.publishEvent(event);
    }

    /**
     * 获取aop代理对象
     *
     * @return 代理对象
     */
    public static <T> T getCurrentProxy() {
        return (T) AopContext.currentProxy();
    }

    public static boolean isProd() {
        return KiteEnv.PROD.getEnv().equals(getActiveProfile());
    }

    public static String getActiveProfile() {
        assert context != null;
        String[] profiles = context.getEnvironment().getActiveProfiles();
        return profiles.length > 0 ? profiles[0] : "";
    }

}
