package com.kite.libai.provider.community.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum CommentEventType {

    /**
     * CommentEventType
     */
    CREATED("created", "创建评论"),
    DELETED("deleted", "删除评论");

    private final String type;
    private final String desc;
}
