package com.kite.libai.boot.event.configuration;

import com.google.common.eventbus.AsyncEventBus;
import com.google.common.eventbus.EventBus;
import com.kite.libai.boot.event.core.KiteEventService;
import lombok.AllArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
@AllArgsConstructor
public class KiteEventAutoConfiguration {

    private EventBus eventBus;

    private AsyncEventBus asyncEventBus;

    @Bean
    KiteEventService build() {
        return new KiteEventService(eventBus, asyncEventBus);
    }
}
