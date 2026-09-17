package com.kite.libai.provider.community.event.model;

import com.kite.libai.boot.event.model.BaseEvent;

import lombok.Getter;

@Getter
public class FollowerEvent implements BaseEvent {

    private String type;

    private Long accountId;

    private Long friendId;

    public FollowerEvent(String type, Long accountId, Long friendId) {
        this.type = type;
        this.accountId = accountId;
        this.friendId = friendId;
    }
}
