package com.kite.libai.provider.system.cache;

import java.util.List;

import com.kite.libai.provider.system.service.SensitiveWordService;
import org.springframework.stereotype.Component;


import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Component
@AllArgsConstructor
public class SensitiveWordCache {


    private final SensitiveWordService sensitiveWordService;

    /**
     * 查询违禁词列表
     *
     * @return List<String>
     */
    public List<String> getSensitiveWordList() {
        return sensitiveWordService.getSensitiveWordList();
    }
}
