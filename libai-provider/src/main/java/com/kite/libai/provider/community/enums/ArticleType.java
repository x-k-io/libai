package com.kite.libai.provider.community.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum ArticleType {

    /**
     * ArticleType
     */
    ARTICLE("article", "文章"),
    VIDEO("video", "视频");

    private final String type;
    private final String desc;
}
