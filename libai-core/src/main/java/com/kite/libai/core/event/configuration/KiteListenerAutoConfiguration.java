package com.kite.libai.core.event.configuration;

import com.google.common.eventbus.AsyncEventBus;
import com.google.common.eventbus.EventBus;
import com.kite.libai.core.event.core.BaseListener;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Configuration;

import jakarta.annotation.PostConstruct;
import java.util.List;

@Slf4j
@Configuration
public class KiteListenerAutoConfiguration {

    private final EventBus eventBus;

    private final AsyncEventBus asyncEventBus;

    private final List<BaseListener> listeners;

    public KiteListenerAutoConfiguration(@Qualifier("eventBus") EventBus eventBus,
                                         @Qualifier("asyncEventBus") AsyncEventBus asyncEventBus,
                                         List<BaseListener> listeners) {
        this.eventBus = eventBus;
        this.asyncEventBus = asyncEventBus;
        this.listeners = listeners;
    }

    @PostConstruct
    public void init() {
        log.info("初始化事件总线==>注册消息总线");
        listeners.forEach(h -> {
            eventBus.register(h);
            asyncEventBus.register(h);
            log.info("{}注册消息总线", h.getClass().getSimpleName());
        });
    }
}
