package com.kite.libai.provider.community.event.model;

import com.kite.libai.core.event.model.BaseEvent;
import com.kite.libai.provider.community.model.entity.Comment;

import lombok.Getter;

@Getter
public class CommentEvent implements BaseEvent {

    private String type;

    private Comment comment;

    public CommentEvent(String type, Comment comment) {
        this.type = type;
        this.comment = comment;
    }

    @Override
    public boolean check() {
        return this.comment != null;
    }
}
