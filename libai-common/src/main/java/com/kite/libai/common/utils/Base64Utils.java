package com.kite.libai.common.utils;

import lombok.experimental.UtilityClass;

import java.nio.charset.Charset;
import java.nio.charset.StandardCharsets;
import java.util.Base64;

@UtilityClass
public class Base64Utils {
    private static final Charset DEFAULT_CHARSET;

    static {
        DEFAULT_CHARSET = StandardCharsets.UTF_8;
    }

    /**
     * 编码
     *
     * @param value 字符串
     * @return {String}
     */
    public static String encode(String value) {
        return Base64Utils.encode(value, Charsets.UTF_8);
    }

    /**
     * 编码
     *
     * @param value   字符串
     * @param charset 字符集
     * @return {String}
     */
    public static String encode(String value, Charset charset) {
        byte[] val = value.getBytes(charset);
        return new String(Base64Utils.encode(val), charset);
    }

    /**
     * 编码URL安全
     *
     * @param value 字符串
     * @return {String}
     */
    public static String encodeUrlSafe(String value) {
        return Base64Utils.encodeUrlSafe(value, Charsets.UTF_8);
    }

    /**
     * 编码URL安全
     *
     * @param value   字符串
     * @param charset 字符集
     * @return {String}
     */
    public static String encodeUrlSafe(String value, Charset charset) {
        byte[] val = value.getBytes(charset);
        return new String(Base64Utils.encodeUrlSafe(val), charset);
    }

    /**
     * 解码
     *
     * @param value 字符串
     * @return {String}
     */
    public static String decode(String value) {
        return Base64Utils.decode(value, Charsets.UTF_8);
    }

    /**
     * 解码
     *
     * @param value   字符串
     * @param charset 字符集
     * @return {String}
     */
    public static String decode(String value, Charset charset) {
        byte[] val = value.getBytes(charset);
        byte[] decodedValue = Base64Utils.decode(val);
        return new String(decodedValue, charset);
    }

    /**
     * 解码URL安全
     *
     * @param value 字符串
     * @return {String}
     */
    public static String decodeUrlSafe(String value) {
        return Base64Utils.decodeUrlSafe(value, Charsets.UTF_8);
    }

    /**
     * 解码URL安全
     *
     * @param value   字符串
     * @param charset 字符集
     * @return {String}
     */
    public static String decodeUrlSafe(String value, Charset charset) {
        byte[] val = value.getBytes(charset);
        byte[] decodedValue = Base64Utils.decodeUrlSafe(val);
        return new String(decodedValue, charset);
    }

    public static byte[] encode(byte[] src) {
        return src.length == 0 ? src : Base64.getEncoder().encode(src);
    }

    public static byte[] decode(byte[] src) {
        return src.length == 0 ? src : Base64.getDecoder().decode(src);
    }

    public static byte[] encodeUrlSafe(byte[] src) {
        return src.length == 0 ? src : Base64.getUrlEncoder().encode(src);
    }

    public static byte[] decodeUrlSafe(byte[] src) {
        return src.length == 0 ? src : Base64.getUrlDecoder().decode(src);
    }

    public static byte[] decodeFromString(String src) {
        return src.isEmpty() ? new byte[0] : decode(src.getBytes(DEFAULT_CHARSET));
    }

    public static String encodeToString(byte[] src) {
        return src.length == 0 ? "" : new String(encode(src), DEFAULT_CHARSET);
    }

}
