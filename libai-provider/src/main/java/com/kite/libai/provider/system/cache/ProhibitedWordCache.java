package com.kite.libai.provider.system.cache;

import java.util.List;
import java.util.concurrent.TimeUnit;

import com.kite.libai.provider.system.service.ProhibitedWordService;
import org.springframework.stereotype.Component;

import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Component
@AllArgsConstructor
public class ProhibitedWordCache {

    private final ProhibitedWordService prohibitedWordService;

    /**
     * 查询违禁词列表
     *
     * @return List<String>
     */
    public List<String> getProhibitedWordList() {
        return prohibitedWordService.getProhibitedWordList();
    }
}
