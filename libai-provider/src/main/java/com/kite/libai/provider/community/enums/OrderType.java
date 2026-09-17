package com.kite.libai.provider.community.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum OrderType {

    /**
     * OrderType
     */
    COMPOSITE("composite", "综合"),
    HOT("hot", "最热"),
    NEW("new", "最新");

    private final String type;
    private final String desc;
}
