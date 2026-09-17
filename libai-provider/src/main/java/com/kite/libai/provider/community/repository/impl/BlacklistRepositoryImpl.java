package com.kite.libai.provider.community.repository.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.kite.libai.boot.repository.MybatisBaseRepository;
import com.kite.libai.provider.community.mapper.BlacklistMapper;
import com.kite.libai.provider.community.model.entity.Blacklist;
import com.kite.libai.provider.community.repository.BlacklistRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class BlacklistRepositoryImpl extends MybatisBaseRepository<BlacklistMapper, Blacklist> implements BlacklistRepository {
    @Override
    public Blacklist getBlacklist(Long accountId, Long theirId) {
        LambdaQueryWrapper<Blacklist> wrapper = new LambdaQueryWrapper<>();
        wrapper.eq(Blacklist::getAccountId, accountId).eq(Blacklist::getTheirId, theirId);
        return this.baseMapper.selectOne(wrapper);
    }

    @Override
    public List<Blacklist> getBlacklistByAccountId(Long accountId) {
        LambdaQueryWrapper<Blacklist> wrapper = new LambdaQueryWrapper<>();
        wrapper.eq(Blacklist::getAccountId, accountId);
        return this.baseMapper.selectList(wrapper);
    }
}
