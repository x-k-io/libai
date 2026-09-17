package com.kite.libai.boot.cache;

import lombok.AllArgsConstructor;
import lombok.Getter;
import org.springframework.util.ObjectUtils;
import org.springframework.util.StringUtils;

import java.time.Duration;

@Getter
@AllArgsConstructor
public enum CacheKeys {
    /**
     * 登录授权
     */
    OAUTH_LOGIN_CODE("kite-oauth-login-code:", "登录授权", 5 * 60),

    /**
     * 三方授权信息
     */
    OAUTH_SESSION_KEY("kite-oauth-session-key:", "三方授权信息", 5 * 60),

    /**
     * 滑动验证码缓存
     */
    CAPTCHA_CODE_KEY("kite-captcha-code-key:", "滑动验证码缓存", 5 * 60),

    /**
     * 短信验证码缓存
     */
    SMS_CODE_KEY("kite-sms-code-key:", "短信验证码缓存", 5 * 60),

    /**
     * 关注Feed
     */
    FOLLOWERS_ARTICLE_KEY("kite-index-follower-feed-key:", "关注用户文章缓存", 30 * 24 * 60 * 60),

    /**
     * 用户推荐
     */
    USER_RECOMMEND_KEY("kite-user-recommend-key:", "用户推荐", 30 * 24 * 60 * 60),

    /**
     * 微信 accessToken 缓存
     */
    WE_CHAT_ACCESS_TOKEN__KEY("kite-we-chat-access-token-key:", "微信 accessToken 缓存", 60 * 110),

    /**
     * 话题 Feed
     */
    ENTRY_TOPIC_FEED_KEY("kite-index-topic-feed-key:", "话题 Feed", 30 * 24 * 60 * 60),

    BIND_MOBILE_TICKET_KEY("kite-bind-mobile-ticket-key:", "绑定手机号令牌", 5 * 60);

    /**
     * key前缀
     */
    private final String keyPrefix;
    /**
     * 描述
     */
    private final String description;
    /**
     * 失效时间
     */
    private final Integer expireSeconds;

    public CacheKey get(Object... suffix) {
        String key = this.keyPrefix;
        // 拼接参数
        if (!ObjectUtils.isEmpty(suffix)) {
            key = key.concat(StringUtils.arrayToDelimitedString(suffix, "_"));
        }
        return this.expireSeconds == null ? new CacheKey(key) : new CacheKey(key, Duration.ofSeconds(expireSeconds));
    }


    public String getKey(Object... suffix) {
        return get(suffix).getKey();
    }
}
