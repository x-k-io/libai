package com.kite.libai.provider.common.repository.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.conditions.update.LambdaUpdateWrapper;
import com.kite.libai.core.repository.MybatisBaseRepository;
import com.kite.libai.provider.common.mapper.IdAllocMapper;
import com.kite.libai.provider.common.model.entity.IdAlloc;
import com.kite.libai.provider.common.repository.IdAllocRepository;
import org.springframework.stereotype.Repository;

@Repository
public class IdAllocRepositoryImpl extends MybatisBaseRepository<IdAllocMapper, IdAlloc> implements IdAllocRepository {
    @Override
    public IdAlloc getByBizType(String bizType) {
        LambdaQueryWrapper<IdAlloc> queryWrapper = new LambdaQueryWrapper<>();
        queryWrapper.eq(IdAlloc::getBizType, bizType);
        return this.baseMapper.selectOne(queryWrapper);
    }

    @Override
    public boolean update(String bizType, Long maxId, Long version) {
        LambdaUpdateWrapper<IdAlloc> updateWrapper = new LambdaUpdateWrapper<>();
        updateWrapper.set(IdAlloc::getMaxId, maxId);
        updateWrapper.set(IdAlloc::getVersion, version + 1);
        updateWrapper.eq(IdAlloc::getBizType, bizType);
        updateWrapper.eq(IdAlloc::getVersion, version);
        return this.update(updateWrapper);
    }


}
