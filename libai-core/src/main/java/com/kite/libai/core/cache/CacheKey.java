package com.kite.libai.core.cache;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.ToString;
import org.springframework.lang.Nullable;

import java.time.Duration;

@Getter
@ToString
@AllArgsConstructor
public class CacheKey {

    public static final String SEPARATOR = ":";

    /**
     * redis key
     */
    private String key;
    /**
     * 超时时间 秒
     */
    @Nullable
    private Duration expire;

    public CacheKey(String key) {
        this.key = key;
    }


    public String getFullKey(Object suffix) {
        if (suffix == null) {
            return key;
        }
        String suf = suffix.toString();
        return key + (suf.startsWith(SEPARATOR) ? suf : (SEPARATOR + suf));
    }
}
