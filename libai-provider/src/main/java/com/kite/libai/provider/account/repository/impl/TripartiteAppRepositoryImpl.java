package com.kite.libai.provider.account.repository.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.kite.libai.core.repository.MybatisBaseRepository;
import com.kite.libai.provider.account.mapper.TripartiteAppMapper;
import com.kite.libai.provider.account.model.entity.TripartiteApp;
import com.kite.libai.provider.account.repository.TripartiteAppRepository;
import org.springframework.stereotype.Repository;

@Repository
public class TripartiteAppRepositoryImpl extends MybatisBaseRepository<TripartiteAppMapper, TripartiteApp> implements TripartiteAppRepository {
    @Override
    public TripartiteApp getByAppCode(String appCode) {
        LambdaQueryWrapper<TripartiteApp> wrapper = new LambdaQueryWrapper<>();
        wrapper.eq(TripartiteApp::getAppCode, appCode);
        return this.baseMapper.selectOne(wrapper);
    }
}
