package com.kite.libai.provider.community.repository.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.conditions.update.LambdaUpdateWrapper;
import com.kite.libai.core.repository.MybatisBaseRepository;
import com.kite.libai.provider.community.enums.ArticleStatus;
import com.kite.libai.provider.community.enums.EntryStatus;
import com.kite.libai.provider.community.enums.OrderType;
import com.kite.libai.common.result.PageResult;
import com.kite.libai.provider.community.mapper.EntryMapper;
import com.kite.libai.provider.community.model.entity.Entry;
import com.kite.libai.provider.community.model.request.EntryQueryParam;
import com.kite.libai.provider.community.repository.EntryRepository;
import org.apache.commons.collections4.CollectionUtils;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;
import java.util.List;


@Repository
public class EntryRepositoryImpl extends MybatisBaseRepository<EntryMapper, Entry> implements EntryRepository {
    @Override
    public boolean updateStatus(Long id, String status) {
        LambdaUpdateWrapper<Entry> updateWrapper = new LambdaUpdateWrapper<>();
        updateWrapper.set(Entry::getStatus, status);
        if (EntryStatus.RELEASED.getStatus().equalsIgnoreCase(status)) {
            updateWrapper.set(Entry::getReleasedAt, LocalDateTime.now());
        }
        updateWrapper.eq(Entry::getId, id);
        return this.update(updateWrapper);
    }

    @Override
    public List<Entry> batchGetByIdAndStatus(List<Long> entryIds, String status) {
        LambdaQueryWrapper<Entry> queryWrapper = new LambdaQueryWrapper<>();
        queryWrapper.in(Entry::getId, entryIds).eq(Entry::getStatus, status);
        return this.baseMapper.selectList(queryWrapper);
    }

    @Override
    public List<Entry> getEntriesByAccountAndReleasedAt(Long accountId, LocalDateTime releasedAt) {
        LambdaQueryWrapper<Entry> wrapper = new LambdaQueryWrapper<>();
        wrapper.eq(Entry::getStatus, ArticleStatus.RELEASED.getStatus())
                .eq(Entry::getAuthorId, accountId)
                .gt(Entry::getReleasedAt, releasedAt);
        return this.baseMapper.selectList(wrapper);
    }

    @Override
    public List<Entry> getEntriesByAccountAndReleasedAt(List<Long> accountIds, LocalDateTime releasedAt) {
        LambdaQueryWrapper<Entry> wrapper = new LambdaQueryWrapper<>();
        wrapper.eq(Entry::getStatus, ArticleStatus.RELEASED.getStatus())
                .in(Entry::getAuthorId, accountIds).
                gt(Entry::getReleasedAt, releasedAt);
        return this.baseMapper.selectList(wrapper);
    }

    @Override
    public PageResult<Entry> pageGetEntries(EntryQueryParam queryParam) {
        LambdaQueryWrapper<Entry> wrapper = new LambdaQueryWrapper<>();
        if (queryParam.getAuthorId() != null) {
            wrapper.eq(Entry::getAuthorId, queryParam.getAuthorId());
        }
        if (queryParam.getCity() != null) {
            wrapper.eq(Entry::getCity, queryParam.getCity());
        }
        if (queryParam.getChannelId() != null) {
            wrapper.eq(Entry::getChannelId, queryParam.getChannelId());
        }
        if (queryParam.getCircleId() != null) {
            wrapper.eq(Entry::getCircleId, queryParam.getCircleId());
        }
        if (CollectionUtils.isNotEmpty(queryParam.getStatusList())) {
            wrapper.in(Entry::getStatus, queryParam.getStatusList());
        }
        if (OrderType.COMPOSITE.getType().equals(queryParam.getOrderType())) {
            wrapper.orderByDesc(Entry::getCompositeOrders);
        } else if (OrderType.HOT.getType().equals(queryParam.getOrderType())) {
            wrapper.orderByDesc(Entry::getHotOrders);
        } else if (OrderType.NEW.getType().equals(queryParam.getOrderType())) {
            wrapper.orderByDesc(Entry::getNewOrders);
        } else {
            wrapper.orderByDesc(Entry::getCreatedAt);
        }
        return this.pageGet(wrapper, queryParam.getPageNum(), queryParam.getPageSize());
    }
}
