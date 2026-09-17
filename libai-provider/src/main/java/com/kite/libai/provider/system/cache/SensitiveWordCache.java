package com.kite.libai.provider.system.cache;

import java.util.List;
import java.util.concurrent.TimeUnit;

import com.kite.libai.provider.system.service.SensitiveWordService;
import org.springframework.stereotype.Component;

import com.github.benmanes.caffeine.cache.Cache;
import com.github.benmanes.caffeine.cache.Caffeine;

import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Component
@AllArgsConstructor
public class SensitiveWordCache {

    private static final String KITE_PROHIBITED_WORD_KEY = "kite:prohibited_word:key";

    private final Cache<String, List<String>> cache = Caffeine.newBuilder()
            .expireAfterWrite(10 * 60L, TimeUnit.SECONDS).maximumSize(10).build();

    private final SensitiveWordService sensitiveWordService;

    /**
     * 查询违禁词列表
     *
     * @return List<String>
     */
    public List<String> getSensitiveWordList() {
        return cache.get(KITE_PROHIBITED_WORD_KEY, x -> sensitiveWordService.getSensitiveWordList());
    }
}
