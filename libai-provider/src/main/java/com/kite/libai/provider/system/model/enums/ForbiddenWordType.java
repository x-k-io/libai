package com.kite.libai.provider.system.model.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum ForbiddenWordType {
    /**
     * ForbiddenWordType
     */
    THREE_DAY("threeDay", 3, "禁言3天"),
    SEVEN_DAY("sevenDay", 7, "禁言7天"),
    PERMANENT("permanent", 365 * 100, "永久禁言");

    private final String type;
    private final int day;
    private final String desc;

    public static ForbiddenWordType getByType(String type) {
        ForbiddenWordType[] values = ForbiddenWordType.values();
        for (ForbiddenWordType forbiddenWordType : values) {
            if (forbiddenWordType.getType().equals(type)) {
                return forbiddenWordType;
            }
        }
        return null;
    }
}
