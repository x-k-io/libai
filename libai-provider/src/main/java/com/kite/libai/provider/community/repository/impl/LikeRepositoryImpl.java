package com.kite.libai.provider.community.repository.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.kite.libai.boot.repository.MybatisBaseRepository;
import com.kite.libai.provider.community.mapper.LikeMapper;
import com.kite.libai.provider.community.model.entity.Like;
import com.kite.libai.provider.community.repository.LikeRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class LikeRepositoryImpl extends MybatisBaseRepository<LikeMapper, Like> implements LikeRepository {
    @Override
    public Like getLike(Long accountId, Long entityId) {
        LambdaQueryWrapper<Like> wrapper = new LambdaQueryWrapper<>();
        wrapper.eq(Like::getAccountId, accountId)
                .eq(Like::getEntityId, entityId);
        return this.baseMapper.selectOne(wrapper);

    }

    @Override
    public List<Like> getLikeList(Long accountId, List<Long> entityIds) {
        LambdaQueryWrapper<Like> wrapper = new LambdaQueryWrapper<>();
        wrapper.eq(Like::getAccountId, accountId)
                .in(Like::getEntityId, entityIds);
        return this.baseMapper.selectList(wrapper);
    }
}
