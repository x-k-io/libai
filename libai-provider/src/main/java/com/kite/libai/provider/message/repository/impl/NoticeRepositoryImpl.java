package com.kite.libai.provider.message.repository.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.kite.libai.boot.repository.MybatisBaseRepository;
import com.kite.libai.provider.message.enums.NoticeType;
import com.kite.libai.core.result.PageResult;
import com.kite.libai.provider.message.mapper.NoticeMapper;
import com.kite.libai.provider.message.model.entity.Notice;
import com.kite.libai.provider.message.repository.NoticeRepository;
import org.apache.commons.collections4.CollectionUtils;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class NoticeRepositoryImpl extends MybatisBaseRepository<NoticeMapper, Notice> implements NoticeRepository {
    @Override
    public PageResult<Notice> getNotices(Long accountId, List<String> types, int pageNum, int pageSize) {
        if (CollectionUtils.isEmpty(types) || accountId == null) {
            return PageResult.success();
        }
        LambdaQueryWrapper<Notice> wrapper = new LambdaQueryWrapper<>();
        wrapper.in(Notice::getType, types);
        if (!types.contains(NoticeType.SYSTEM.getType())) {
            wrapper.eq(Notice::getReceiveId, accountId);
        } else {
            wrapper.in(Notice::getReceiveId, accountId, null);
        }
        wrapper.orderByDesc(Notice::getCreatedAt);
        return this.pageGet(wrapper, pageNum, pageSize);
    }
}
