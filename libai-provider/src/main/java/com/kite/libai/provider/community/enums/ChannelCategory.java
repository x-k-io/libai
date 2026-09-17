package com.kite.libai.provider.community.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum ChannelCategory {

    /**
     * EntryCategory
     */
    UGC("ugc", "用户创作内容"),
    PGC("pgc", "专业创作内容"),
    OGC("ogc", "职业创作内容");

    private final String code;
    private final String name;
}
