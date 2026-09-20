package com.kite.libai.provider.community.repository.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.kite.libai.core.repository.MybatisBaseRepository;
import com.kite.libai.common.result.PageResult;
import com.kite.libai.provider.community.mapper.BrowseMapper;
import com.kite.libai.provider.community.model.entity.Browse;
import com.kite.libai.provider.community.repository.BrowseRepository;
import org.springframework.stereotype.Repository;

@Repository
public class BrowseRepositoryImpl extends MybatisBaseRepository<BrowseMapper, Browse> implements BrowseRepository {
    @Override
    public PageResult<Browse> pageGetBrowseByAccountId(Long accountId, int pageNum, int pageSize) {
        LambdaQueryWrapper<Browse> wrapper = new LambdaQueryWrapper<>();
        wrapper.eq(Browse::getAccountId, accountId);
        wrapper.orderByDesc(Browse::getCreatedAt);
        return this.pageGet(wrapper, pageNum, pageSize);
    }
}
