package com.kite.libai.provider.community.event.producer;

import org.springframework.stereotype.Service;

import com.kite.libai.core.event.core.KiteEventService;
import com.kite.libai.provider.community.event.model.PlayEvent;
import com.kite.libai.provider.community.model.entity.Entry;

import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
@AllArgsConstructor
public class PlayEventProducer {

    private final KiteEventService kiteEventService;

    /**
     * 发送作品播放事件
     *
     * @param entry entry
     */
    public void sendBrowseEvent(Entry entry) {
        PlayEvent event = new PlayEvent(entry);
        kiteEventService.post(event);
    }
}
