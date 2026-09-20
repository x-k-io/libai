package com.kite.libai.provider.system.repository.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.kite.libai.core.repository.MybatisBaseRepository;
import com.kite.libai.provider.system.mapper.ProhibitedWordMapper;
import com.kite.libai.provider.system.model.entity.ProhibitedWord;
import com.kite.libai.provider.system.repository.ProhibitedWordRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class ProhibitedRepositoryImpl extends MybatisBaseRepository<ProhibitedWordMapper, ProhibitedWord> implements ProhibitedWordRepository {
    @Override
    public List<ProhibitedWord> getByStatus(Boolean status) {
        LambdaQueryWrapper<ProhibitedWord> wrapper = new LambdaQueryWrapper<>();
        wrapper.eq(ProhibitedWord::getStatus, Boolean.TRUE);
        return this.baseMapper.selectList(wrapper);
    }
}
