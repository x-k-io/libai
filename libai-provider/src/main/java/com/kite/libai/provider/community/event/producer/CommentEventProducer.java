package com.kite.libai.provider.community.event.producer;

import org.springframework.stereotype.Service;

import com.kite.libai.boot.event.core.KiteEventService;
import com.kite.libai.provider.community.enums.CommentEventType;
import com.kite.libai.provider.community.event.model.CommentEvent;
import com.kite.libai.provider.community.model.entity.Comment;

import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
@AllArgsConstructor
public class CommentEventProducer {

    private final KiteEventService kiteEventService;

    /**
     * 发送创建评论事件
     *
     * @param comment comment
     */
    public void sendCreateCommentEvent(Comment comment) {
        CommentEvent event = new CommentEvent(CommentEventType.CREATED.getType(), comment);
        kiteEventService.post(event);
    }

    /**
     * 发送删除评论事件
     *
     * @param comment comment
     */
    public void sendDeleteCommentEvent(Comment comment) {
        CommentEvent event = new CommentEvent(CommentEventType.DELETED.getType(), comment);
        kiteEventService.post(event);
    }
}
