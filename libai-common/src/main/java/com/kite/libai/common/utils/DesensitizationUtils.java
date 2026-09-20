package com.kite.libai.common.utils;


import org.apache.commons.lang3.StringUtils;

import java.util.Arrays;

public class DesensitizationUtils {

    /**
     * [中文姓名] 只显示第一个汉字，其他隐藏为2个星号<例子：李**>
     *
     * @param fullName 全名
     * @return 脱敏后的字符串
     */

    public static String chineseName(final String fullName) {
        if (StringUtils.isBlank(fullName)) {
            return StringPool.EMPTY;
        }
        return sensitive(fullName, 1, 0, CharPool.STAR);
    }

    /**
     * [身份证号] 显示最后四位，其他隐藏。共计18位或者15位。<例子：*************5762>
     *
     * @param id 身份证号
     * @return 脱敏后的字符串
     */

    public static String idCardNum(final String id) {
        if (StringUtils.isBlank(id)) {
            return StringPool.EMPTY;
        }
        return sensitive(id, 0, 4, CharPool.STAR);
    }

    /**
     * [固定电话] 后四位，其他隐藏<例子：****1234>
     *
     * @param num 固定电话号
     * @return 脱敏后的字符串
     */

    public static String phoneNo(final String num) {
        if (StringUtils.isBlank(num)) {
            return StringPool.EMPTY;
        }
        return sensitive(num, 0, 4, CharPool.STAR);
    }

    /**
     * [手机号码] 前三位，后四位，其他隐藏<例子:138****1234>
     *
     * @param num 手机号
     * @return 脱敏后的字符串
     */

    public static String mobileNo(final String num) {
        if (StringUtils.isBlank(num)) {
            return StringPool.EMPTY;
        }
        return sensitive(num, 3, 4, CharPool.STAR);
    }

    /**
     * [地址] 只显示到地区，不显示详细地址；我们要对个人信息增强保护<例子：北京市海淀区****>
     *
     * @param address       地区
     * @param sensitiveSize 敏感信息长度
     * @return 脱敏后的字符串
     */

    public static String address(final String address, final int sensitiveSize) {
        if (StringUtils.isBlank(address)) {
            return StringPool.EMPTY;
        }
        return sensitive(address, 0, sensitiveSize, CharPool.STAR);
    }

    /**
     * [电子邮箱] 邮箱前缀仅显示第一个字母，前缀其他隐藏，用星号代替，@及后面的地址显示<例子:g**@163.com>
     *
     * @param email 邮箱
     * @return 脱敏后的字符串
     */

    public static String email(final String email) {
        if (StringUtils.isBlank(email)) {
            return StringPool.EMPTY;
        }
        final int index = email.indexOf(CharPool.AT);
        if (index <= 1) {
            return email;
        } else {
            return sensitive(email, 1, email.length() - index, CharPool.STAR);
        }
    }

    /**
     * [银行卡号] 前六位，后四位，其他用星号隐藏每位1个星号<例子:622260***********1234>
     *
     * @param cardNum 银行卡号
     * @return 脱敏后的字符串
     */

    public static String bankCard(final String cardNum) {
        if (StringUtils.isBlank(cardNum)) {
            return StringPool.EMPTY;
        }
        return sensitive(cardNum, 6, 4, CharPool.STAR);
    }

    /**
     * [公司开户银行联号] 公司开户银行联行号,显示前两位，其他用星号隐藏，每位1个星号<例子:12********>
     *
     * @param code 银行联行号
     * @return 脱敏后的字符串
     */

    public static String cnapsCode(final String code) {
        if (StringUtils.isBlank(code)) {
            return StringPool.EMPTY;
        }
        return sensitive(code, 2, 0, CharPool.STAR);
    }

    /**
     * 右边脱敏
     *
     * @param sensitiveStr 待脱敏的字符串
     * @return 脱敏后的字符串
     */

    public static String right(final String sensitiveStr) {
        if (StringUtils.isBlank(sensitiveStr)) {
            return StringPool.EMPTY;
        }
        int length = sensitiveStr.length();
        return sensitive(sensitiveStr, length / 2, 0, CharPool.STAR);
    }

    /**
     * 左边脱敏
     *
     * @param sensitiveStr 待脱敏的字符串
     * @return 脱敏后的字符串
     */

    public static String left(final String sensitiveStr) {
        if (StringUtils.isBlank(sensitiveStr)) {
            return StringPool.EMPTY;
        }
        int length = sensitiveStr.length();
        return sensitive(sensitiveStr, 0, length / 2, CharPool.STAR);
    }

    /**
     * 中间脱敏，保留两端
     *
     * @param sensitiveStr 待脱敏的字符串
     * @return 脱敏后的字符串
     */

    public static String middle(final String sensitiveStr) {
        if (StringUtils.isBlank(sensitiveStr)) {
            return StringPool.EMPTY;
        }
        int length = sensitiveStr.length();
        if (length < 3) {
            return StringUtils.leftPad(StringPool.EMPTY, length, CharPool.STAR);
        }
        char[] chars = new char[length];
        int last = length - 1;
        Arrays.fill(chars, 1, last, CharPool.STAR);
        chars[0] = sensitiveStr.charAt(0);
        chars[last] = sensitiveStr.charAt(last);
        return new String(chars);
    }

    /**
     * 全部脱敏
     *
     * @param sensitiveStr 待脱敏的字符串
     * @return 脱敏后的字符串
     */

    public static String all(final String sensitiveStr) {
        if (StringUtils.isBlank(sensitiveStr)) {
            return StringPool.EMPTY;
        }
        return sensitive(sensitiveStr, 0, 0, CharPool.STAR);
    }

    private static String sensitive(String str, int fromIndex, int lastSize, char padChar) {
        int length = str.length();
        // 全部脱敏
        if (fromIndex == 0 && lastSize == 0) {
            return StringUtils.repeat(CharPool.STAR, length);
        }
        int toIndex = length - lastSize;
        // 头部脱敏
        if (fromIndex == 0) {
            String tail = str.substring(toIndex);
            return StringUtils.repeat(padChar, toIndex - fromIndex).concat(tail);
        }
        // 尾部脱敏
        if (toIndex == length) {
            String head = str.substring(0, fromIndex);
            return head.concat(StringUtils.repeat(padChar, toIndex - fromIndex));
        }
        // 中部
        String head = str.substring(0, fromIndex);
        String tail = str.substring(toIndex);
        return head + StringUtils.repeat(padChar, toIndex - fromIndex) + tail;
    }

}
