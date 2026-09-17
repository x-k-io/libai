package com.kite.libai.provider.community.event.model;

import com.kite.libai.boot.event.model.BaseEvent;
import com.kite.libai.provider.community.model.entity.Like;

import lombok.Getter;

@Getter
public class LikeEvent implements BaseEvent {

    private String type;

    private Long accountId;

    private String entityType;

    private Long entityId;

    private Long entryId;

    public LikeEvent(String type, Like like) {
        this.type = type;
        this.accountId = like.getAccountId();
        this.entityType = like.getEntityType();
        this.entityId = like.getEntityId();
        this.entryId = like.getEntryId();
    }
}
