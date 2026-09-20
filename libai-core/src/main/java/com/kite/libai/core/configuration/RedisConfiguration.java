package com.kite.libai.core.configuration;

import com.kite.libai.core.cache.LibaiRedisTemplate;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.connection.RedisConnectionFactory;
import org.springframework.data.redis.serializer.StringRedisSerializer;

@Configuration
public class RedisConfiguration {
    @Bean
    public LibaiRedisTemplate libaiRedisTemplate(RedisConnectionFactory factory) {
        LibaiRedisTemplate template = new LibaiRedisTemplate();
        template.setConnectionFactory(factory);
        // 设置键的序列化方式
        template.setKeySerializer(new StringRedisSerializer());
        // 设置值的序列化方式
        template.setValueSerializer(new StringRedisSerializer());

        // 设置Hash的key的序列化方式
        template.setHashKeySerializer(new StringRedisSerializer());
        // 设置Hash的value的序列化方式
        template.setHashValueSerializer(new StringRedisSerializer());
        return template;
    }
}
