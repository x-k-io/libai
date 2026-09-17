package com.kite.libai.boot.event.core;

import com.google.common.eventbus.AsyncEventBus;
import com.google.common.eventbus.EventBus;
import com.kite.libai.core.utils.JsonUtils;
import com.kite.libai.boot.event.model.BaseEvent;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@AllArgsConstructor
public class KiteEventService {

    private EventBus eventBus;

    private AsyncEventBus asyncEventBus;

    /**
     * 发送事件
     *
     * @param event 事件
     */
    public <T extends BaseEvent> void post(BaseEvent event) {
        log.debug("event:{}", JsonUtils.toJson(event));
        if (eventBus == null || asyncEventBus == null) {
            log.error("发送事件，eventBus or asyncEventBus  is null");
            return;
        }
        if (event == null) {
            log.info("发送事件校验不通过 event is null");
            return;
        }
        if (event.async()) {
            asyncEventBus.post(event);
        } else {
            eventBus.post(event);
        }
    }
}
