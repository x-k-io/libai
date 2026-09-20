package com.kite.libai.provider.community.event.producer;

import org.springframework.stereotype.Service;

import com.kite.libai.core.event.core.KiteEventService;
import com.kite.libai.provider.community.enums.LikeEventType;
import com.kite.libai.provider.community.event.model.LikeEvent;
import com.kite.libai.provider.community.model.entity.Like;

import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
@AllArgsConstructor
public class LikeEventProducer {

    private final KiteEventService kiteEventService;

    /**
     * 发送点赞事件
     *
     * @param like like
     */
    public void sendLikeEvent(Like like) {
        LikeEvent event = new LikeEvent(LikeEventType.LIKED.getType(), like);
        kiteEventService.post(event);
    }

    /**
     * 发送取消点赞事件
     *
     * @param like like
     */
    public void sendCancelLikeEvent(Like like) {
        LikeEvent event = new LikeEvent(LikeEventType.CANCEL.getType(), like);
        kiteEventService.post(event);
    }
}
