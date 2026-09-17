package com.kite.libai.provider.community.event.model;

import com.kite.libai.boot.event.model.BaseEvent;
import com.kite.libai.provider.community.model.entity.Favorite;

import lombok.Getter;

@Getter
public class FavoriteEvent implements BaseEvent {

    private String type;

    private String entityType;

    private Long entityId;

    private Long entryId;

    public FavoriteEvent(String type, Favorite favorite) {
        this.type = type;
        this.entityType = favorite.getEntityType();
        this.entityId = favorite.getEntityId();
        this.entryId = favorite.getEntryId();
    }
}
