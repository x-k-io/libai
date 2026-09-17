package com.kite.libai.provider.system.presenter;

import java.util.List;
import java.util.stream.Collectors;

import com.kite.libai.core.utils.BeanUtils;
import com.kite.libai.provider.system.model.entity.SensitiveWord;
import com.kite.libai.provider.system.model.request.SensitiveWordRequest;
import com.kite.libai.provider.system.service.SensitiveWordService;

import org.springframework.stereotype.Component;

import com.kite.libai.core.exception.ServiceException;
import com.kite.libai.core.result.SystemCode;
import org.apache.commons.collections4.CollectionUtils;

import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Component
@AllArgsConstructor
public class SensitiveWordPresenter {

    private final SensitiveWordService sensitiveWordService;

    /**
     * 创建敏感词
     *
     * @param sensitiveWordRequest sensitiveWordRequest
     * @return boolean
     */
    public boolean create(SensitiveWordRequest sensitiveWordRequest) {
        SensitiveWord sensitiveWord = BeanUtils.copy(sensitiveWordRequest, SensitiveWord.class);
        sensitiveWord.setStatus(Boolean.FALSE);
        return sensitiveWordService.save(sensitiveWord);
    }


    /**
     * 创建违禁词
     *
     * @param sensitiveWordRequests sensitiveWordRequests
     * @return boolean
     */
    public boolean batchSave(List<SensitiveWordRequest> sensitiveWordRequests) {
        if (CollectionUtils.isEmpty(sensitiveWordRequests)) {
            return Boolean.TRUE;
        }
        List<SensitiveWord> sensitiveWords = sensitiveWordRequests.stream()
                .map(x -> {
                    SensitiveWord sensitiveWord = BeanUtils.copy(x, SensitiveWord.class);
                    sensitiveWord.setStatus(Boolean.TRUE);
                    return sensitiveWord;
                }).collect(Collectors.toList());
        return sensitiveWordService.saveBatch(sensitiveWords);
    }

    /**
     * 更新敏感词
     *
     * @param id                   id
     * @param sensitiveWordRequest sensitiveWordRequest
     * @return boolean
     */
    public boolean updateById(Long id, SensitiveWordRequest sensitiveWordRequest) {
        SensitiveWord sensitiveWord = sensitiveWordService.getById(id);
        if (sensitiveWord == null) {
            throw new ServiceException("敏感词不存在");
        }
        BeanUtils.copy(sensitiveWordRequest, sensitiveWord);
        sensitiveWord.setStatus(Boolean.FALSE);
        return sensitiveWordService.updateById(sensitiveWord);
    }

    /**
     * 删除敏感词
     *
     * @param id id
     * @return boolean
     */
    public boolean deleteById(Long id) {
        return sensitiveWordService.deleteById(id);
    }

    /**
     * 查询敏感词详情
     *
     * @param id id
     * @return SensitiveWord
     */
    public SensitiveWord getById(Long id) {
        return sensitiveWordService.getById(id);
    }

}
