package com.kite.libai.provider.community.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum EntryStatus {

    /**
     * ArticleStatus
     */
    DRAFT("draft", "草稿"),
    WAIT_REVIEW("wait_review", "待审核"),
    UNDER_REVIEW("under_review", "审核中"),
    SUSPECTED("suspected", "疑似"),
    RELEASED("released", "发布"),
    FAIL("fail", "未通过"),
    DELETED("deleted", "已删除");

    private final String status;
    private final String desc;
}
