package com.kite.libai.core.utils;

import org.apache.commons.lang3.StringUtils;

public class SystemUtils {
    /**
     * 代码部署于 linux 上，工作默认为 mac 和 Windows
     */
    private static final String OS_NAME_LINUX = "LINUX";

    /**
     * 获取 user home
     */
    public static final String USER_HOME = getSystemProperty("user.home");

    /**
     * 获取用户地址
     */
    public static final String USER_DIR = getSystemProperty("user.dir");

    /**
     * 获取用户名
     */
    public static final String USER_NAME = getSystemProperty("user.name");

    /**
     * os 名
     */
    public static final String OS_NAME = getSystemProperty("os.name");

    /**
     * Gets a System property, defaulting to {@code null} if the property cannot be read.
     * <p>
     * If a {@code SecurityException} is caught, the return value is {@code null} and a message is
     * written to
     * {@code System.err}.
     * </p>
     *
     * @param property the system property name
     * @return the system property value or {@code null} if a security problem occurs
     */
    private static String getSystemProperty(final String property) {
        try {
            return System.getProperty(property);
        } catch (final SecurityException ex) {
            return null;
        }
    }

    /**
     * 判断是否为本地开发环境
     *
     * @return booleans
     */
    public static boolean isLinux() {
        return StringUtils.isNotBlank(OS_NAME) && OS_NAME_LINUX.equals(OS_NAME.toUpperCase());
    }

    /**
     * 代码部署于 linux 上，工作默认为 mac 和 Windows
     *
     * @return boolean
     */
    public static boolean isLocalDev() {
        return !SystemUtils.isLinux();
    }
}
