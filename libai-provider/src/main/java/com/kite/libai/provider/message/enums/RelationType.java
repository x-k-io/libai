package com.kite.libai.provider.message.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum RelationType {

    /**
     * RelationType
     */
    STRANGER("stranger", "陌生人"),
    FOLLOWER("follower", "关注"),
    FRIEND("friend", "好友");

    private final String type;
    private final String desc;
}
