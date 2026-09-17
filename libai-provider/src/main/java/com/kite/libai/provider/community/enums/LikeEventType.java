package com.kite.libai.provider.community.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum LikeEventType {

    /**
     * LikeEventType
     */
    LIKED("liked", "点赞"),
    CANCEL("cancel", "取消点赞");

    private final String type;
    private final String desc;
}
