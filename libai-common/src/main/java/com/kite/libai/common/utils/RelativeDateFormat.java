package com.kite.libai.common.utils;

import java.time.LocalDateTime;
import java.time.ZoneOffset;
import java.util.Date;

public class RelativeDateFormat {

    private static final long ONE_MINUTE = 60000L;
    private static final long ONE_HOUR = 3600000L;
    private static final long ONE_DAY = 86400000L;
    private static final long ONE_WEEK = 604800000L;

    private static final String SECOND_AGO = "秒前";
    private static final String MINUTE_AGO = "分钟前";
    private static final String HOUR_AGO = "小时前";
    private static final String DAY_AGO = "天前";
    private static final String WEEK_AGO = "周前";
    private static final String MONTH_AGO = "个月前";
    private static final String YEAR_AGO = "年前";

    public static String format(Date date) {
        return format(date.getTime());
    }

    public static String format(LocalDateTime localDateTime) {
        long epochMilli = localDateTime.toInstant(ZoneOffset.of("+8")).toEpochMilli();
        return format(epochMilli);
    }

    private static String format(long epochMilli) {

        long delta = System.currentTimeMillis() - epochMilli;

        if (delta < ONE_MINUTE / 6L) {
            return "刚刚";
        }

        if (delta < ONE_MINUTE) {
            long seconds = toSeconds(delta);
            return (seconds <= 0 ? 1 : seconds) + SECOND_AGO;
        }
        if (delta < 45L * ONE_MINUTE) {
            long minutes = toMinutes(delta);
            return (minutes <= 0 ? 1 : minutes) + MINUTE_AGO;
        }
        if (delta < 24L * ONE_HOUR) {
            long hours = toHours(delta);
            return (hours <= 0 ? 1 : hours) + HOUR_AGO;
        }

        if (delta < 48L * ONE_HOUR) {
            return "昨天";
        }

        if (delta < 7L * ONE_DAY) {
            long days = toDays(delta);
            return (days <= 0 ? 1 : days) + DAY_AGO;
        }

        if (delta < 30L * ONE_DAY) {
            long weeks = toWeeks(delta);
            return (weeks <= 0 ? 1 : weeks) + WEEK_AGO;
        }

        if (delta < 12L * 4L * ONE_WEEK) {
            long months = toMonths(delta);
            return (months <= 0 ? 1 : months) + MONTH_AGO;
        } else {
            long years = toYears(delta);
            return (years <= 0 ? 1 : years) + YEAR_AGO;
        }
    }


    private static long toSeconds(long date) {
        return date / 1000L;
    }

    private static long toMinutes(long date) {
        return toSeconds(date) / 60L;
    }

    private static long toHours(long date) {
        return toMinutes(date) / 60L;
    }

    private static long toDays(long date) {
        return toHours(date) / 24L;
    }

    private static long toWeeks(long date) {
        return toDays(date) / 7L;
    }

    private static long toMonths(long date) {
        return toDays(date) / 30L;
    }

    private static long toYears(long date) {
        return toMonths(date) / 365L;
    }
}
