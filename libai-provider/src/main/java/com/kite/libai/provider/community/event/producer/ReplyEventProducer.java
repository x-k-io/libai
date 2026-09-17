package com.kite.libai.provider.community.event.producer;

import com.kite.libai.boot.event.core.KiteEventService;
import com.kite.libai.provider.community.model.entity.Reply;
import org.springframework.stereotype.Service;

import com.kite.libai.provider.community.enums.ReplyEventType;
import com.kite.libai.provider.community.event.model.ReplyEvent;

import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
@AllArgsConstructor
public class ReplyEventProducer {

    private final KiteEventService kiteEventService;

    /**
     * 发送创建回复事件
     *
     * @param reply reply
     */
    public void sendCreateReplyEvent(Reply reply) {
        ReplyEvent event = new ReplyEvent(ReplyEventType.CREATED.getType(), reply);
        kiteEventService.post(event);
    }

    /**
     * 发送删除回复事件
     *
     * @param reply reply
     */
    public void sendDeleteReplyEvent(Reply reply) {
        ReplyEvent event = new ReplyEvent(ReplyEventType.DELETED.getType(), reply);
        kiteEventService.post(event);
    }
}
