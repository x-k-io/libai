package com.kite.libai.core.utils;

public class ObjectUtils {
    /**
     * 强转string
     *
     * @param object Object
     * @return String
     */

    public static String toStr(Object object) {
        return toStr(object, null);
    }

    /**
     * 强转string
     *
     * @param object       Object
     * @param defaultValue 默认值
     * @return String
     */

    public static String toStr(Object object, String defaultValue) {
        if (null == object) {
            return defaultValue;
        }
        if (object instanceof CharSequence) {
            return ((CharSequence) object).toString();
        }
        return String.valueOf(object);
    }

    /**
     * 对象转为 int （支持 String 和 Number），默认: 0
     *
     * @param object Object
     * @return int
     */
    public static int toInt(Object object) {
        return toInt(object, 0);
    }

    /**
     * 对象转为 int （支持 String 和 Number）
     *
     * @param object       Object
     * @param defaultValue 默认值
     * @return int
     */
    public static int toInt(Object object, int defaultValue) {
        if (object instanceof Number) {
            return ((Number) object).intValue();
        }
        if (object instanceof CharSequence) {
            String value = ((CharSequence) object).toString();
            try {
                return Integer.parseInt(value);
            } catch (final NumberFormatException nfe) {
                return defaultValue;
            }
        }
        return defaultValue;
    }

    /**
     * 对象转为 long （支持 String 和 Number），默认: 0L
     *
     * @param object Object
     * @return long
     */
    public static long toLong(Object object) {
        return toLong(object, 0L);
    }

    /**
     * 对象转为 long （支持 String 和 Number），默认: 0L
     *
     * @param object Object
     * @return long
     */
    public static long toLong(Object object, long defaultValue) {
        if (object instanceof Number) {
            return ((Number) object).longValue();
        }
        if (object instanceof CharSequence) {
            String value = ((CharSequence) object).toString();
            try {
                return Long.parseLong(value);
            } catch (final NumberFormatException nfe) {
                return defaultValue;
            }
        }
        return defaultValue;
    }

    /**
     * 对象转为 Float
     *
     * @param object Object
     * @return 结果
     */
    public static float toFloat(Object object) {
        return toFloat(object, 0.0f);
    }

    /**
     * 对象转为 Float
     *
     * @param object       Object
     * @param defaultValue float
     * @return 结果
     */
    public static float toFloat(Object object, float defaultValue) {
        if (object instanceof Number) {
            return ((Number) object).floatValue();
        }
        if (object instanceof CharSequence) {
            String value = ((CharSequence) object).toString();
            try {
                return Float.parseFloat(value);
            } catch (NumberFormatException nfe) {
                return defaultValue;
            }
        }
        return defaultValue;
    }

    /**
     * 对象转为 Double
     *
     * @param object Object
     * @return 结果
     */
    public static double toDouble(Object object) {
        return toDouble(object, 0.0d);
    }

    /**
     * 对象转为 Double
     *
     * @param object       Object
     * @param defaultValue double
     * @return 结果
     */
    public static double toDouble(Object object, double defaultValue) {
        if (object instanceof Number) {
            return ((Number) object).doubleValue();
        }
        if (object instanceof CharSequence) {
            String value = ((CharSequence) object).toString();
            try {
                return Double.parseDouble(value);
            } catch (NumberFormatException nfe) {
                return defaultValue;
            }
        }
        return defaultValue;
    }

    /**
     * 对象转为 Byte
     *
     * @param object Object
     * @return 结果
     */
    public static byte toByte(Object object) {
        return toByte(object, (byte) 0);
    }

    /**
     * 对象转为 Byte
     *
     * @param object       Object
     * @param defaultValue byte
     * @return 结果
     */
    public static byte toByte(Object object, byte defaultValue) {
        if (object instanceof Number) {
            return ((Number) object).byteValue();
        }
        if (object instanceof CharSequence) {
            String value = ((CharSequence) object).toString();
            try {
                return Byte.parseByte(value);
            } catch (NumberFormatException nfe) {
                return defaultValue;
            }
        }
        return defaultValue;
    }

    /**
     * 对象转为 Short
     *
     * @param object Object
     * @return 结果
     */
    public static short toShort(Object object) {
        return toShort(object, (short) 0);
    }

    /**
     * 对象转为 Short
     *
     * @param object       Object
     * @param defaultValue short
     * @return 结果
     */
    public static short toShort(Object object, short defaultValue) {
        if (object instanceof Number) {
            return ((Number) object).byteValue();
        }
        if (object instanceof CharSequence) {
            String value = ((CharSequence) object).toString();
            try {
                return Short.parseShort(value);
            } catch (NumberFormatException nfe) {
                return defaultValue;
            }
        }
        return defaultValue;
    }

    /**
     * 对象转为 Boolean
     *
     * @param object Object
     * @return 结果
     */

    public static Boolean toBoolean(Object object) {
        return toBoolean(object, null);
    }

    /**
     * 对象转为 Boolean
     *
     * @param object       Object
     * @param defaultValue 默认值
     * @return 结果
     */

    public static Boolean toBoolean(Object object, Boolean defaultValue) {
        if (object instanceof Boolean) {
            return (Boolean) object;
        }
        if (object instanceof CharSequence) {
            String value = ((CharSequence) object).toString();
            try {
                return Boolean.parseBoolean(value.trim());
            } catch (NumberFormatException nfe) {
                return defaultValue;
            }
        }
        return defaultValue;
    }
}
