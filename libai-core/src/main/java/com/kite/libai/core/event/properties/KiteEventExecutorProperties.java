package com.kite.libai.core.event.properties;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;

import java.util.concurrent.TimeUnit;

@Data
@ConfigurationProperties("kite.event")
public class KiteEventExecutorProperties {
    /**
     * 异步核心线程数，默认：2
     */
    private int corePoolSize = 5;
    /**
     * 异步最大线程数，默认：50
     */
    private int maximumPoolSize = 200;
    /**
     * 队列容量，默认：10000
     */
    private int queueCapacity = 1024;
    /**
     * 线程存活时间，默认：300
     */
    private int keepAliveTime = 300;
    /**
     * 线程存活时间单位，默认：300
     */
    private TimeUnit keepAliveUnit = TimeUnit.MILLISECONDS;
}
