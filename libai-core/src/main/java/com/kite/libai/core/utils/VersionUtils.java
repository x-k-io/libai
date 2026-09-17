package com.kite.libai.core.utils;

public class VersionUtils {

    private static final String DELIMITER = "\\.";

    /**
     * 比较版本号是否相同
     *
     * @param v1 v1
     * @param v2 v2
     * @return {boolean}
     */
    public boolean eq(String v1, String v2) {
        return compare(v1, v2) == 0;
    }

    /**
     * 比较版本号是不相同
     *
     * @param v1 v1
     * @param v2 v2
     * @return {boolean}
     */
    public boolean ne(String v1, String v2) {
        return compare(v1, v2) != 0;
    }

    /**
     * v1 是否大于 v2
     *
     * @param v1 v1
     * @param v2 v2
     * @return {boolean}
     */
    public boolean gt(String v1, String v2) {
        return compare(v1, v2) > 0;
    }

    /**
     * v1 是否大于等于 v2
     *
     * @param v1 v1
     * @param v2 v2
     * @return {boolean}
     */
    public boolean gte(String v1, String v2) {
        return compare(v1, v2) >= 0;
    }

    /**
     * v1 是否小于 v2
     *
     * @param v1 v1
     * @param v2 v2
     * @return {boolean}
     */
    public boolean lt(String v1, String v2) {
        return compare(v1, v2) < 0;
    }

    /**
     * v1 是否小于等于 v2
     *
     * @param v1 v1
     * @param v2 v2
     * @return {boolean}
     */
    public boolean lte(String v1, String v2) {
        return compare(v1, v2) <= 0;
    }

    /**
     * 比较2个版本号
     *
     * @param v1 v1
     * @param v2 v2
     * @return (v1 < v2) ? -1 : ((v1 == v2) ? 0 : 1)
     */
    private static int compare(String v1, String v2) {
        // null 视为最小版本
        if (v1 == null) {
            return -1;
        }
        if (v2 == null) {
            return 1;
        }
        if (v1.equals(v2)) {
            return 0;
        }
        // 去除空格
        v1 = v1.trim();
        v2 = v2.trim();
        if (v1.equals(v2)) {
            return 0;
        }
        String[] v1s = v1.split(DELIMITER);
        String[] v2s = v2.split(DELIMITER);
        int v1sLen = v1s.length;
        int v2sLen = v2s.length;
        int len = Math.max(v1sLen, v2sLen);
        for (int i = 0; i < len; i++) {
            String c1 = len > v1sLen || null == v1s[i] ? StringPool.EMPTY : v1s[i];
            String c2 = len > v2sLen || null == v2s[i] ? StringPool.EMPTY : v2s[i];

            int result = c1.compareTo(c2);
            if (result != 0) {
                return result;
            }
        }
        return 0;
    }
}
