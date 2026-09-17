package com.kite.libai.provider.community.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum DataChangeType {

    /**
     * DataChangeType
     */
    PLUS("plus", "增加"),
    REDUCE("reduce", "减少");

    private final String type;
    private final String desc;
}
