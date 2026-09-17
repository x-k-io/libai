package com.kite.libai.provider.account.utils;

import com.github.promeg.pinyinhelper.Pinyin;

import org.apache.commons.lang3.StringUtils;

public class PinyinUtils {

    public static String getFirstLetter(String str) {
        if (StringUtils.isBlank(str)) {
            return "";
        }
        char c = str.charAt(0);
        char r = Pinyin.toPinyin(c).charAt(0);
        return String.valueOf(r).toUpperCase();
    }
}
