package com.kite.libai.provider.community.event.model;

import com.kite.libai.core.event.model.BaseEvent;
import com.kite.libai.provider.community.model.entity.Entry;

import lombok.Getter;

@Getter
public class PlayEvent implements BaseEvent {

    private Long entryId;

    public PlayEvent(Entry entry) {
        this.entryId = entry.getId();
    }
}
