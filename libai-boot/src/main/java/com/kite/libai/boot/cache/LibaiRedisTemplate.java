package com.kite.libai.boot.cache;

import com.kite.libai.core.utils.JsonUtils;

import org.apache.commons.lang3.StringUtils;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.lang.Nullable;

import java.time.Duration;
import java.util.Objects;
import java.util.concurrent.TimeUnit;
import java.util.function.Supplier;

public class LibaiRedisTemplate extends RedisTemplate<String, String> {
    /**
     * 设置缓存
     *
     * @param cacheKey 缓存key
     * @param value    缓存value
     */
    public void set(CacheKey cacheKey, Object value) {
        String key = cacheKey.getKey();
        Duration expire = cacheKey.getExpire();
        if (expire == null) {
            set(key, value);
        } else {
            setEx(key, value, expire);
        }
    }

    /**
     * 存放 key value 对到 redis。
     */
    public void set(String key, Object value) {
        System.out.println(JsonUtils.toJson(value));
        this.opsForValue().set(key, Objects.requireNonNull(JsonUtils.toJson(value)));
    }

    /**
     * 存放 key value 对到 redis，并将 key 的生存时间设为 seconds (以秒为单位)。
     * 如果 key 已经存在， SETEX 命令将覆写旧值。
     */
    public void setEx(String key, Object value, Duration timeout) {
        this.opsForValue().set(key, Objects.requireNonNull(JsonUtils.toJson(value)), timeout);
    }

    /**
     * 存放 key value 对到 redis，并将 key 的生存时间设为 seconds (以秒为单位)。
     * 如果 key 已经存在， SETEX 命令将覆写旧值。
     */
    public void setEx(String key, Object value, Long seconds) {
        this.opsForValue().set(key, Objects.requireNonNull(JsonUtils.toJson(value)), seconds, TimeUnit.SECONDS);
    }

    /**
     * 返回 key 所关联的 value 值
     * 如果 key 不存在那么返回特殊值 nil 。
     */
    @Nullable
    public <T> T get(String key, Class<T> clazz) {
        String value = this.opsForValue().get(key);
        if (StringUtils.isBlank(value)) {
            return null;
        }
        return JsonUtils.parse(value, clazz);
    }

    /**
     * 获取cache 为 null 时使用加载器，然后设置缓存
     *
     * @param key    cacheKey
     * @param loader cache loader
     * @param <T>    泛型
     * @return 结果
     */
    @Nullable
    public <T> T get(String key, Supplier<T> loader, Class<T> clazz) {
        T value = this.get(key, clazz);
        if (value != null) {
            return value;
        }
        value = loader.get();
        if (value == null) {
            return null;
        }
        this.set(key, value);
        return value;
    }


    /**
     * 获取cache 为 null 时使用加载器，然后设置缓存
     *
     * @param cacheKey cacheKey
     * @param loader   cache loader
     * @param <T>      泛型
     * @return 结果
     */
    @Nullable
    public <T> T get(CacheKey cacheKey, Supplier<T> loader, Class<T> clazz) {
        String key = cacheKey.getKey();
        return this.get(key, loader, clazz);
    }

    /**
     * 返回 key 所关联的 value 值
     * 如果 key 不存在那么返回特殊值 nil 。
     */
    @Nullable
    public <T> T get(CacheKey cacheKey, Class<T> clazz) {
        return this.get(cacheKey.getKey(), clazz);
    }

    /**
     * 返回 key 所关联的 value 值
     * 如果 key 不存在那么返回特殊值 nil 。
     */
    @Nullable
    public String get(String key) {
        return this.opsForValue().get(key);
    }

    /**
     * 返回 key 所关联的 value 值
     * 如果 key 不存在那么返回特殊值 nil 。
     */
    @Nullable
    public String get(CacheKey cacheKey) {
        return this.get(cacheKey.getKey());
    }
}
