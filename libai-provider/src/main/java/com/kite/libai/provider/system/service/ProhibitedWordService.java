package com.kite.libai.provider.system.service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.function.Function;
import java.util.stream.Collectors;

import com.kite.libai.core.repository.BaseService;
import com.kite.libai.provider.system.model.entity.ProhibitedWord;
import com.kite.libai.provider.system.repository.ProhibitedWordRepository;
import org.springframework.stereotype.Service;

import org.apache.commons.collections4.CollectionUtils;

import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
@AllArgsConstructor
public class ProhibitedWordService extends BaseService<ProhibitedWordRepository, ProhibitedWord> {

    /**
     * 批量查询违禁词
     *
     * @param ids ids
     * @return Map<Long, ProhibitedWord>
     */
    public Map<Long, ProhibitedWord> batchGet(List<Long> ids) {
        List<ProhibitedWord> list = this.batchGetByIds(ids);
        if (CollectionUtils.isNotEmpty(list)) {
            return list.stream().collect(Collectors.toMap(ProhibitedWord::getId, Function.identity()));
        }
        return new HashMap<>(16);
    }

    /**
     * 查询违禁词列表
     *
     * @return List<String>
     */
    public List<String> getProhibitedWordList() {
        List<ProhibitedWord> list = this.repository.getByStatus(Boolean.TRUE);
        if (CollectionUtils.isEmpty(list)) {
            return new ArrayList<>();
        }
        return list.stream().map(ProhibitedWord::getWord).distinct().collect(Collectors.toList());
    }
}
