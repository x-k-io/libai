package com.kite.libai.provider.system.service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.function.Function;
import java.util.stream.Collectors;

import com.kite.libai.boot.repository.BaseService;
import com.kite.libai.provider.system.model.entity.SensitiveWord;
import com.kite.libai.provider.system.repository.SensitiveWordRepository;
import org.springframework.stereotype.Service;

import org.apache.commons.collections4.CollectionUtils;

import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
@AllArgsConstructor
public class SensitiveWordService extends BaseService<SensitiveWordRepository, SensitiveWord> {

    /**
     * 批量查询敏感词
     *
     * @param ids ids
     * @return Map<Long, SensitiveWord>
     */
    public Map<Long, SensitiveWord> batchGet(List<Long> ids) {
        List<SensitiveWord> list = this.batchGetByIds(ids);
        if (CollectionUtils.isNotEmpty(list)) {
            return list.stream().collect(Collectors.toMap(SensitiveWord::getId, Function.identity()));
        }
        return new HashMap<>(16);
    }

    /**
     * 查询敏感词列表
     *
     * @return List<String>
     */
    public List<String> getSensitiveWordList() {
        List<SensitiveWord> list = this.repository.getByStatus(Boolean.TRUE);
        if (CollectionUtils.isEmpty(list)) {
            return new ArrayList<>();
        }
        return list.stream().map(SensitiveWord::getWord).distinct().collect(Collectors.toList());
    }
}
