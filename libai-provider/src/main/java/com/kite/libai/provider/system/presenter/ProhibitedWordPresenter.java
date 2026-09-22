package com.kite.libai.provider.system.presenter;

import java.util.List;
import java.util.stream.Collectors;

import com.kite.libai.common.utils.BeanUtils;
import com.kite.libai.provider.system.model.entity.ProhibitedWord;
import com.kite.libai.provider.system.model.request.ProhibitedWordRequest;
import com.kite.libai.provider.system.service.ProhibitedWordService;

import org.springframework.stereotype.Component;

import com.kite.libai.common.exception.ServiceException;
import org.apache.commons.collections4.CollectionUtils;

import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Component
@AllArgsConstructor
public class ProhibitedWordPresenter {

    private final ProhibitedWordService prohibitedWordService;

    /**
     * 创建违禁词
     *
     * @param prohibitedWordRequest prohibitedWordRequest
     * @return boolean
     */
    public boolean create(ProhibitedWordRequest prohibitedWordRequest) {
        ProhibitedWord prohibitedWord = BeanUtils.copy(prohibitedWordRequest, ProhibitedWord.class);
        prohibitedWord.setStatus(Boolean.TRUE);
        return prohibitedWordService.insert(prohibitedWord);
    }


    /**
     * 创建违禁词
     *
     * @param prohibitedWordRequests prohibitedWordRequests
     * @return boolean
     */
    public boolean batchSave(List<ProhibitedWordRequest> prohibitedWordRequests) {
        if (CollectionUtils.isEmpty(prohibitedWordRequests)) {
            return Boolean.TRUE;
        }
        List<ProhibitedWord> prohibitedWords = prohibitedWordRequests.stream()
                .map(x -> {
                    ProhibitedWord prohibitedWord = BeanUtils.copy(x, ProhibitedWord.class);
                    prohibitedWord.setStatus(Boolean.TRUE);
                    return prohibitedWord;
                }).collect(Collectors.toList());
        return prohibitedWordService.insert(prohibitedWords);
    }


    /**
     * 更新违禁词
     *
     * @param id                    id
     * @param prohibitedWordRequest prohibitedWordRequest
     * @return boolean
     */
    public boolean updateById(Long id, ProhibitedWordRequest prohibitedWordRequest) {
        ProhibitedWord prohibitedWord = prohibitedWordService.getById(id);
        if (prohibitedWord == null) {
            throw new ServiceException("违禁词不存在");
        }
        BeanUtils.copy(prohibitedWordRequest, prohibitedWord);
        prohibitedWord.setStatus(Boolean.FALSE);
        return prohibitedWordService.updateById(prohibitedWord);
    }

    /**
     * 删除违禁词
     *
     * @param id id
     * @return boolean
     */
    public boolean deleteById(Long id) {
        return prohibitedWordService.deleteById(id);
    }


    /**
     * 查询违禁词详情
     *
     * @param id id
     * @return ProhibitedWord
     */
    public ProhibitedWord getById(Long id) {
        return prohibitedWordService.getById(id);
    }
}
