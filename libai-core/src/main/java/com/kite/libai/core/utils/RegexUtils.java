package com.kite.libai.core.utils;

import lombok.experimental.UtilityClass;

import java.util.Optional;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

@UtilityClass
public final class RegexUtils {

    private static final String PHONE_REGEX = "^1[23456789]\\d{9}$";
    private static final String QQ_REGEX = "^[1-9][0-9]{4,9}$";
    private static final String WEIXIN_REGEX = "^[a-zA-Z\\d_]{5,}$";
    private static final String IP_REGEX =
            "^((25[0-5]|2[0-4]\\d|((1\\d{2})|([1-9]?\\d)))\\.){3}(25[0-5]|2[0-4]\\d|((1\\d{2})|([1-9]?\\d)))$";
    /**
     * 身份证校验，初级校验，具体规则有一套算法
     */
    public static final String ID_CARD = "^\\d{15}$|^\\d{17}([0-9]|X)$";
    /**
     * 用户昵称规范正则表达式：昵称，1-16字，支持中英文、数字，不支持emoji
     */
    public static final String NICK_NAME = "[\\u4e00-\\u9fa5_a-zA-Z0-9_-]{1,16}";
    /**
     * 密码
     */
    public static final String PWD = "^(?![a-zA-Z]+$)(?!\\d+$)(?![\\W_]+$)\\S{8,20}$";
    /**
     * 验证邮箱
     */
    public static final String EMAIL = "^[a-zA-Z0-9_-]+@[a-zA-Z0-9_-]+(\\.[a-zA-Z0-9_-]+)+$";
    /**
     * 护照正则表达式
     * 14/15开头 + 7位数字, G + 8位数字, P + 7位数字, S/D + 7或8位数字,等
     */
    public static final String PASS_PORT = "^([a-zA-z]|[0-9]){5,17}$";
    /**
     * 军官证正则表达式
     * 军/兵/士/文/职/广/（其他中文） + "字第" + 4到8位字母或数字 + "号"
     */
    public static final String MILITARY_OFFICER_CARD = "^[\\u4E00-\\u9FA5](字第)([0-9a-zA-Z]{4,8})(号?)$";
    /**
     * 台胞证
     */
    public static final String MTPS = "^[a-zA-Z][0-9]{9}$";
    /**
     * 香港通行证
     * H + 10位或6位数字
     */
    public static final String HONG_KONG_ID_CARD = "^[Hh]{1}([0-9]{10}|[0-9]{6})$";
    /**
     * 澳门通行证
     * M + 10位或6位数字
     */
    public static final String MACAU_ID_CARD = "^[Mm]{1}([0-9]{10}|[0-9]{6})$";

    /**
     * 是手机号
     *
     * @param phone 手机号
     * @return phone为null或不能够匹配phone格式，返回一false，否则返回true
     */
    public static boolean isPhone(String phone) {
        return Optional.ofNullable(phone)
                .map(x -> x.matches(PHONE_REGEX))
                .orElse(Boolean.FALSE);
    }

    /**
     * 正则校验QQ号格式
     *
     * @param qq QQ号码
     * @return boolean
     */
    public static boolean isQQ(String qq) {
        return Optional.ofNullable(qq)
                .map(x -> x.matches(QQ_REGEX))
                .orElse(Boolean.FALSE);
    }

    /**
     * 校验微信号
     *
     * @param weixin 微信号
     * @return boolean
     */
    public static boolean isWeixin(String weixin) {
        return Optional.ofNullable(weixin)
                .map(x -> x.matches(WEIXIN_REGEX))
                .orElse(Boolean.FALSE);
    }

    /**
     * 正则校验IP地址格式
     *
     * @param ip IP地址
     * @return boolean
     */
    public static boolean isIp(String ip) {
        return Optional.ofNullable(ip)
                .map(x -> x.matches(IP_REGEX))
                .orElse(Boolean.FALSE);
    }

    /**
     * 编译传入正则表达式和字符串去匹配,忽略大小写
     *
     * @param regex        正则
     * @param beTestString 字符串
     * @return {boolean}
     */
    public static boolean match(String regex, String beTestString) {
        Pattern pattern = Pattern.compile(regex, Pattern.CASE_INSENSITIVE);
        Matcher matcher = pattern.matcher(beTestString);
        return matcher.matches();
    }

    /**
     * 编译传入正则表达式在字符串中寻找，如果匹配到则为true
     *
     * @param regex        正则
     * @param beTestString 字符串
     * @return {boolean}
     */
    public static boolean find(String regex, String beTestString) {
        Pattern pattern = Pattern.compile(regex);
        Matcher matcher = pattern.matcher(beTestString);
        return matcher.find();
    }

    /**
     * 编译传入正则表达式在字符串中寻找，如果找到返回第一个结果找不到返回null
     *
     * @param regex         正则
     * @param beFoundString 字符串
     * @return {boolean}
     */
    public static String findResult(String regex, String beFoundString) {
        Pattern pattern = Pattern.compile(regex);
        Matcher matcher = pattern.matcher(beFoundString);
        if (matcher.find()) {
            return matcher.group();
        }
        return null;
    }
}
