package com.kite.libai.provider.community.service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.function.Function;
import java.util.stream.Collectors;

import com.kite.libai.core.repository.BaseService;
import com.kite.libai.common.result.PageResult;
import com.kite.libai.provider.community.model.entity.Browse;
import com.kite.libai.provider.community.repository.BrowseRepository;
import org.springframework.stereotype.Service;

import org.apache.commons.collections4.CollectionUtils;

import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
@AllArgsConstructor
public class BrowseService extends BaseService<BrowseRepository, Browse> {

    /**
     * 批量查询浏览记录
     *
     * @param ids ids
     * @return Map<Long, Browse>
     */
    public Map<Long, Browse> batchGet(List<Long> ids) {
        List<Browse> list = this.batchGetByIds(ids);
        if (CollectionUtils.isNotEmpty(list)) {
            return list.stream().collect(Collectors.toMap(Browse::getId, Function.identity()));
        }
        return new HashMap<>(16);
    }

    public PageResult<Browse> pageGetBrowseByAccountId(Long accountId, int pageNum, int pageSize) {
        return this.repository.pageGetBrowseByAccountId(accountId, pageNum, pageSize);
    }
}
