package com.kite.libai.provider.community.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum EntityType {

    /**
     * EntityType
     */
    ENTRY("entry", "作品"),
    ARTICLE("article", "动态"),
    COMMENT("comment", "评论"),
    REPLY("reply", "回复");

    private final String type;
    private final String desc;
}
