package com.kite.libai.provider.community.event.model;

import com.kite.libai.core.event.model.BaseEvent;
import com.kite.libai.provider.community.enums.EntryEventType;
import com.kite.libai.provider.community.model.entity.Entry;

import lombok.Getter;

@Getter
public class EntryEvent implements BaseEvent {

    private Long authorId;

    private String type;

    private String authorType;

    private String entityType;

    private Long entityId;

    private Long entryId;

    private String eventType;

    public EntryEvent() {

    }

    public static EntryEvent build(Entry entry, EntryEventType type) {
        EntryEvent event = new EntryEvent();
        event.authorId = entry.getAuthorId();
        event.type = entry.getType();
        event.authorType = entry.getAuthorType();
        event.entityType = entry.getEntityType();
        event.entityId = entry.getEntityId();
        event.entryId = entry.getId();
        event.eventType = type.getType();
        return event;
    }
}
