package com.kite.libai.core.event.configuration;

import com.google.common.eventbus.AsyncEventBus;
import com.google.common.eventbus.EventBus;
import com.google.common.util.concurrent.ThreadFactoryBuilder;
import com.kite.libai.common.utils.JsonUtils;
import com.kite.libai.core.event.properties.KiteEventExecutorProperties;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.ThreadFactory;
import java.util.concurrent.ThreadPoolExecutor;

@Slf4j
@Configuration
@AllArgsConstructor
@EnableConfigurationProperties({
        KiteEventExecutorProperties.class
})
public class KiteBusAutoConfiguration {

    private KiteEventExecutorProperties executorProperties;

    @Bean
    EventBus eventBus() {
        return new EventBus();
    }

    @Bean
    AsyncEventBus asyncEventBus() {
        log.info("初始化异步事件总线==>线程池配置:{}", JsonUtils.toJson(executorProperties));
        if (executorProperties == null) {
            throw new IllegalStateException("KiteEventExecutorProperties configuration cannot be null");
        }
        ThreadFactory namedThreadFactory = new ThreadFactoryBuilder()
                .setNameFormat("kite-event-bus-pool-%d").build();
        ExecutorService pool =
                new ThreadPoolExecutor(executorProperties.getCorePoolSize(), executorProperties.getMaximumPoolSize(),
                        executorProperties.getKeepAliveTime(), executorProperties.getKeepAliveUnit(),
                        new LinkedBlockingQueue<>(executorProperties.getQueueCapacity()), namedThreadFactory,
                        new ThreadPoolExecutor.AbortPolicy());
        return new AsyncEventBus(pool);
    }
}
