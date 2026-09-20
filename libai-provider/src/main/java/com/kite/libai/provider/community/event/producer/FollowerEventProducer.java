package com.kite.libai.provider.community.event.producer;

import org.springframework.stereotype.Service;

import com.kite.libai.core.event.core.KiteEventService;
import com.kite.libai.provider.community.enums.FollowerEventType;
import com.kite.libai.provider.community.event.model.FollowerEvent;

import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
@AllArgsConstructor
public class FollowerEventProducer {

    private final KiteEventService kiteEventService;

    /**
     * 发送关注事件
     *
     * @param accountId accountId
     * @param friendId  friendId
     */
    public void sendFollowerEvent(Long accountId, Long friendId) {
        FollowerEvent event = new FollowerEvent(FollowerEventType.FOLLOWER.getType(), accountId, friendId);
        kiteEventService.post(event);
    }

    /**
     * 发送取消关注事件
     *
     * @param accountId accountId
     * @param friendId  friendId
     */
    public void sendCancelFollowerEvent(Long accountId, Long friendId) {
        FollowerEvent event = new FollowerEvent(FollowerEventType.CANCEL_FOLLOWER.getType(), accountId, friendId);
        kiteEventService.post(event);
    }
}
