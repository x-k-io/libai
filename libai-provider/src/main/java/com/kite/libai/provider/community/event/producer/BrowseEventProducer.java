package com.kite.libai.provider.community.event.producer;

import org.springframework.stereotype.Service;

import com.kite.libai.boot.event.core.KiteEventService;
import com.kite.libai.provider.community.event.model.BrowseEvent;
import com.kite.libai.provider.community.model.entity.Entry;

import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
@AllArgsConstructor
public class BrowseEventProducer {

    private final KiteEventService kiteEventService;

    /**
     * 发送作品浏览事件
     *
     * @param accountId 浏览者id
     * @param entry     entry
     */
    public void sendBrowseEvent(Long accountId, Entry entry) {
        BrowseEvent event = new BrowseEvent(accountId, entry);
        kiteEventService.post(event);
    }
}
