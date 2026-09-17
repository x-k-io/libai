package com.kite.libai.provider.system.repository.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.kite.libai.boot.repository.MybatisBaseRepository;
import com.kite.libai.provider.system.mapper.SensitiveWordMapper;
import com.kite.libai.provider.system.model.entity.SensitiveWord;
import com.kite.libai.provider.system.repository.SensitiveWordRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class SensitiveRepositoryImpl extends MybatisBaseRepository<SensitiveWordMapper, SensitiveWord> implements SensitiveWordRepository {
    @Override
    public List<SensitiveWord> getByStatus(Boolean status) {
        LambdaQueryWrapper<SensitiveWord> wrapper = new LambdaQueryWrapper<>();
        wrapper.eq(SensitiveWord::getStatus, Boolean.TRUE);
        return this.baseMapper.selectList(wrapper);
    }
}
