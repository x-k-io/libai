package com.kite.libai.provider.community.event.listener;

import com.kite.libai.provider.community.event.model.ReplyEvent;
import com.kite.libai.provider.community.model.entity.Comment;
import com.kite.libai.provider.community.service.CommentService;
import org.springframework.stereotype.Component;

import com.google.common.eventbus.Subscribe;
import com.kite.libai.core.event.core.BaseListener;
import com.kite.libai.provider.community.enums.DataType;
import com.kite.libai.provider.community.enums.EntityType;
import com.kite.libai.provider.community.enums.LikeEventType;
import com.kite.libai.provider.community.enums.ReplyEventType;
import com.kite.libai.provider.community.event.model.LikeEvent;

import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Component
@AllArgsConstructor
public class CommentDataChangeListener implements BaseListener {

    private final CommentService commentService;


    /**
     * 累计回复量数据
     *
     * @param replyEvent replyEvent
     */
    @Subscribe
    public void process(ReplyEvent replyEvent) {
        if (replyEvent == null) {
            return;
        }
        if (ReplyEventType.CREATED.getType().equals(replyEvent.getType())) {
            commentService.add(replyEvent.getCommentId(), DataType.REPLIES);
        } else {
            commentService.reduce(replyEvent.getCommentId(), DataType.REPLIES);
        }
    }

    /**
     * 累计点赞量数据
     *
     * @param likeEvent likeEvent
     */
    @Subscribe
    public void process(LikeEvent likeEvent) {
        if (likeEvent == null) {
            return;
        }
        if (EntityType.COMMENT.getType().equals(likeEvent.getEntityType())) {
            Comment comment = commentService.getById(likeEvent.getEntityId());
            if (comment == null) {
                return;
            }
            if (LikeEventType.LIKED.getType().equals(likeEvent.getType())) {
                commentService.add(likeEvent.getEntityId(), DataType.LIKES);
            } else {
                commentService.reduce(likeEvent.getEntityId(), DataType.LIKES);
            }
        }
    }
}
