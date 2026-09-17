package com.kite.libai.provider.community.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum ReplyEventType {

    /**
     * ReplyEventType
     */
    CREATED("created", "创建回复"),
    DELETED("deleted", "删除回复");

    private final String type;
    private final String desc;
}
