package com.kite.libai.provider.community.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum FriendRelationType {

    /**
     * FriendRelationType
     */
    FRIEND("friend", "好友"),
    FOLLOWING("following", "粉丝"),
    FOLLOWER("follower", "关注"),
    BLACKLIST("blacklist", "黑名单");

    private final String type;
    private final String desc;
}
