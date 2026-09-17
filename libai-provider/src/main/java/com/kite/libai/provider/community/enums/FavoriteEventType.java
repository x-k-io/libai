package com.kite.libai.provider.community.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum FavoriteEventType {

    /**
     * FavoriteEventType
     */
    FAVORITES("favorites", "收藏"),
    CANCEL("cancel", "取消收藏");

    private final String type;
    private final String desc;
}
