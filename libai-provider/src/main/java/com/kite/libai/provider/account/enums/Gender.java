package com.kite.libai.provider.account.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum Gender {
    /**
     * Gender
     */
    UNKNOWN("unknown", "未知"),
    FEMALE("female", "女"),
    MALE("male", "男");

    private final String key;
    private final String desc;

    public static String getGender(Integer gender) {
        if (gender == null || gender == 0) {
            return Gender.UNKNOWN.key;
        }
        if (gender == 1) {
            return Gender.MALE.key;
        }
        if (gender == 2) {
            return Gender.FEMALE.key;
        }
        return Gender.UNKNOWN.key;
    }
}
