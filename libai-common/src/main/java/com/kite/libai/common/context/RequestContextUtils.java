package com.kite.libai.common.context;


import com.kite.libai.common.model.KiteAccount;
import org.apache.commons.lang3.StringUtils;

import java.util.Locale;

public class RequestContextUtils {

    private RequestContextUtils() {
    }

    public static String getToken() {
        if (RequestContextHolder.get() == null) {
            return StringUtils.EMPTY;
        }
        return RequestContextHolder.get().getToken();
    }

    public static String getDeviceId() {
        if (RequestContextHolder.get() == null) {
            return StringUtils.EMPTY;
        }
        return RequestContextHolder.get().getDeviceId();
    }

    public static String getNonce() {
        if (RequestContextHolder.get() == null) {
            return StringUtils.EMPTY;
        }
        return RequestContextHolder.get().getNonce();
    }

    public static Long getTimestamp() {
        if (RequestContextHolder.get() == null) {
            return null;
        }
        return RequestContextHolder.get().getTimestamp();
    }

    public static String getSign() {
        if (RequestContextHolder.get() == null) {
            return StringUtils.EMPTY;
        }
        return RequestContextHolder.get().getSign();
    }

    public static String getTraceId() {
        if (RequestContextHolder.get() == null) {
            return StringUtils.EMPTY;
        }
        return RequestContextHolder.get().getTraceId();
    }

    public static String getAppName() {
        if (RequestContextHolder.get() == null) {
            return StringUtils.EMPTY;
        }
        return RequestContextHolder.get().getAppName();
    }

    public static String getAppVersion() {
        if (RequestContextHolder.get() == null) {
            return StringUtils.EMPTY;
        }
        return RequestContextHolder.get().getAppVersion();
    }

    public static String getTimeZone() {
        if (RequestContextHolder.get() == null) {
            return StringUtils.EMPTY;
        }
        return RequestContextHolder.get().getTimeZone();
    }

    public static String getIp() {
        if (RequestContextHolder.get() == null) {
            return StringUtils.EMPTY;
        }
        return RequestContextHolder.get().getIp();
    }

    public static Locale getLocale() {
        if (RequestContextHolder.get() == null) {
            return Locale.getDefault();
        }
        return RequestContextHolder.get().getLocale();
    }

    public static String getUserAgent() {
        if (RequestContextHolder.get() == null) {
            return StringUtils.EMPTY;
        }
        return RequestContextHolder.get().getUserAgent();
    }

    public static KiteAccount getAccount() {
        if (RequestContextHolder.get() == null) {
            return null;
        }
        return RequestContextHolder.get().getAccount();
    }

    public static Long getAccountId() {
        KiteAccount account = getAccount();
        if (account == null) {
            return null;
        }
        return account.getId();
    }


    public static String getAccountName() {
        KiteAccount account = getAccount();
        if (account == null) {
            return null;
        }
        return account.getName();
    }

    public static Long getTenantId() {
        KiteAccount account = getAccount();
        if (account == null) {
            return null;
        }
        return account.getTenantId();
    }
}
