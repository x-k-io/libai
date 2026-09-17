package com.kite.libai.core.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum ViewType {

    /**
     * ViewType
     */
    LIST("list", "列表"),
    TREE("tree", "树形"),
    ;

    private final String type;
    private final String desc;
}
