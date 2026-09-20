package com.kite.libai.common.utils;

import lombok.experimental.UtilityClass;

import java.util.concurrent.TimeUnit;

@UtilityClass
public class ThreadUtils {

    /**
     * Thread sleep
     *
     * @param millis 时长
     */
    public static void sleep(long millis) {
        try {
            Thread.sleep(millis);
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        }
    }

    /**
     * Thread sleep
     *
     * @param timeUnit TimeUnit
     * @param timeout  timeout
     */
    public static void sleep(TimeUnit timeUnit, long timeout) {
        try {
            timeUnit.sleep(timeout);
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        }
    }

    /**
     * 获取当前线程id 字符串形式
     *
     * @return threadId
     */
    public static Long getId() {
        return Thread.currentThread().getId();
    }
}
