package com.kite.libai.core.event.configuration;

import com.google.common.eventbus.AsyncEventBus;
import com.google.common.eventbus.EventBus;
import com.kite.libai.core.event.core.KiteEventService;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class KiteEventAutoConfiguration {

    private final EventBus eventBus;

    private final AsyncEventBus asyncEventBus;

    public KiteEventAutoConfiguration(@Qualifier("eventBus") EventBus eventBus,
                                      @Qualifier("asyncEventBus") AsyncEventBus asyncEventBus) {
        this.eventBus = eventBus;
        this.asyncEventBus = asyncEventBus;
    }

    @Bean
    KiteEventService build() {
        return new KiteEventService(eventBus, asyncEventBus);
    }
}
