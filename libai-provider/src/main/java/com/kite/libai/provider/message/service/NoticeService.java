package com.kite.libai.provider.message.service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.function.Function;
import java.util.stream.Collectors;

import com.kite.libai.core.repository.BaseService;
import com.kite.libai.common.result.PageResult;
import com.kite.libai.provider.message.model.entity.Notice;
import com.kite.libai.provider.message.repository.NoticeRepository;
import org.springframework.stereotype.Service;

import org.apache.commons.collections4.CollectionUtils;

import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
@AllArgsConstructor
public class NoticeService extends BaseService<NoticeRepository, Notice> {

    /**
     * 批量查询通知
     *
     * @param ids ids
     * @return Map<Long, Notice>
     */
    public Map<Long, Notice> batchGet(List<Long> ids) {
        List<Notice> list = this.batchGetByIds(ids);
        if (CollectionUtils.isNotEmpty(list)) {
            return list.stream().collect(Collectors.toMap(Notice::getId, Function.identity()));
        }
        return new HashMap<>(16);
    }

    public PageResult<Notice> getNotices(Long accountId, List<String> types, int pageNum, int pageSize) {
        return this.repository.getNotices(accountId, types, pageNum, pageSize);
    }
}
