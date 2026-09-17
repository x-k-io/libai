package com.kite.libai.provider.community.strategy.factory;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.function.Function;
import java.util.stream.Collectors;

import javax.annotation.PostConstruct;

import com.kite.libai.provider.community.strategy.entry.EntryProcessor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import lombok.extern.slf4j.Slf4j;

@Component
@Slf4j
public class EntryProcessorFactory {

    @Autowired
    private List<EntryProcessor> processors;

    private Map<String, EntryProcessor> processorMap = new HashMap<>();

    @PostConstruct
    public void init() {
        processorMap = processors.stream().collect(Collectors
                .toMap(EntryProcessor::getEntityType, Function.identity()));
    }

    public EntryProcessor getByEntityType(String entityType) {
        EntryProcessor processor = processorMap.get(entityType);
        if (processor == null) {
            log.error("根据实体类型查询作品处理器失败 entityType:{}", entityType);
            return null;
        }
        return processor;
    }
}
