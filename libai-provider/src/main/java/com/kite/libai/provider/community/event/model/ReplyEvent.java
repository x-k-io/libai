package com.kite.libai.provider.community.event.model;

import com.kite.libai.boot.event.model.BaseEvent;
import com.kite.libai.provider.community.model.entity.Reply;

import lombok.Getter;

@Getter
public class ReplyEvent implements BaseEvent {

    private String type;

    private Long entryId;

    private Long commentId;

    public ReplyEvent(String type, Reply reply) {
        this.type = type;
        this.entryId = reply.getEntryId();
        this.commentId = reply.getCommentId();
    }
}
