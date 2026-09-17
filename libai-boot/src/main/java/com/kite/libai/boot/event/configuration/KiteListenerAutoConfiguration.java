package com.kite.libai.boot.event.configuration;

import com.google.common.eventbus.AsyncEventBus;
import com.google.common.eventbus.EventBus;
import com.kite.libai.boot.event.core.BaseListener;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.annotation.Configuration;

import javax.annotation.PostConstruct;
import java.util.List;

@Slf4j
@Configuration
@AllArgsConstructor
public class KiteListenerAutoConfiguration {

    private EventBus eventBus;

    private AsyncEventBus asyncEventBus;

    private List<BaseListener> listeners;

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
