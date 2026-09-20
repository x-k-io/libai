package com.kite.libai.provider.community.repository.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.kite.libai.core.repository.MybatisBaseRepository;
import com.kite.libai.common.result.PageResult;
import com.kite.libai.provider.community.mapper.FavoriteMapper;
import com.kite.libai.provider.community.model.entity.Favorite;
import com.kite.libai.provider.community.repository.FavoriteRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class FavoriteRepositoryImpl extends MybatisBaseRepository<FavoriteMapper, Favorite> implements FavoriteRepository {
    @Override
    public Favorite getByAccountIdAndEntryId(Long accountId, Long entryId) {
        LambdaQueryWrapper<Favorite> wrapper = new LambdaQueryWrapper<>();
        wrapper.eq(Favorite::getAccountId, accountId)
                .eq(Favorite::getEntryId, entryId);
        return this.baseMapper.selectOne(wrapper);
    }

    @Override
    public List<Favorite> getByAccountIdAndEntryIds(Long accountId, List<Long> entryIds) {
        LambdaQueryWrapper<Favorite> wrapper = new LambdaQueryWrapper<>();
        wrapper.eq(Favorite::getAccountId, accountId)
                .in(Favorite::getEntryId, entryIds);
        return this.baseMapper.selectList(wrapper);
    }

    @Override
    public PageResult<Favorite> pageGetFavoriteByAccountId(Long accountId, int pageNum, int pageSize) {
        LambdaQueryWrapper<Favorite> wrapper = new LambdaQueryWrapper<>();
        wrapper.eq(Favorite::getAccountId, accountId);
        wrapper.orderByDesc(Favorite::getCreatedAt);
        return this.pageGet(wrapper, pageNum, pageSize);
    }
}
