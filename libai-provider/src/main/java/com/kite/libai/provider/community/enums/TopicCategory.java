package com.kite.libai.provider.community.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum TopicCategory {

    /**
     * EntryCategory
     */
    UGC("ugc", "用户创作内容", Boolean.TRUE),
    PGC("pgc", "专业创作内容", Boolean.TRUE),
    OTHER("other", "职业创作内容", Boolean.FALSE);

    private final String code;
    private final String name;
    private final boolean enabled;
}
