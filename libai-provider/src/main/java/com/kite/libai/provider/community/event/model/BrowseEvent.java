package com.kite.libai.provider.community.event.model;

import com.kite.libai.core.event.model.BaseEvent;
import com.kite.libai.provider.community.model.entity.Entry;

import lombok.Getter;

@Getter
public class BrowseEvent implements BaseEvent {

    private long accountId;

    private String entityType;

    private Long entityId;

    private Long entryId;

    public BrowseEvent(Long accountId, Entry entry) {
        this.accountId = accountId;
        this.entityType = entry.getEntityType();
        this.entityId = entry.getEntityId();
        this.entryId = entry.getId();
    }
}
