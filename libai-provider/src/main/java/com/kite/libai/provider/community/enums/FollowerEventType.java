package com.kite.libai.provider.community.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum FollowerEventType {

    /**
     * FollowerEventType
     */
    FOLLOWER("follower", "关注"),
    CANCEL_FOLLOWER("cancel_follower", "取消关注");

    private final String type;
    private final String desc;
}
