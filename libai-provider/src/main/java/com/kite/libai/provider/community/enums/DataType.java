package com.kite.libai.provider.community.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum DataType {

    /**
     * DataType
     */
    USERS("users", "用户"),
    ENTRIES("entries", "作品"),
    BROWSES("browses", "浏览"),
    FAVORITES("favorites", "收藏"),
    PLAYS("plays", "播放"),
    LIKES("likes", "点赞"),
    REPLIES("replies", "回复"),
    FOLLOWERS("followers", "被关注数"),
    FOLLOWING("following", "关注数");

    private final String type;
    private final String desc;
}
