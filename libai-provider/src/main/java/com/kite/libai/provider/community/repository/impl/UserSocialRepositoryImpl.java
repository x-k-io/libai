package com.kite.libai.provider.community.repository.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.kite.libai.core.repository.MybatisBaseRepository;
import com.kite.libai.provider.community.mapper.UserSocialMapper;
import com.kite.libai.provider.community.model.entity.UserSocial;
import com.kite.libai.provider.community.repository.UserSocialRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class UserSocialRepositoryImpl extends MybatisBaseRepository<UserSocialMapper, UserSocial> implements UserSocialRepository {

    @Override
    public List<UserSocial> batchGetByAccountId(List<Long> accountIds) {
        LambdaQueryWrapper<UserSocial> wrapper = new LambdaQueryWrapper<>();
        wrapper.in(UserSocial::getAccountId, accountIds);
        return this.baseMapper.selectList(wrapper);
    }

    @Override
    public UserSocial getByAccountId(Long accountId) {
        LambdaQueryWrapper<UserSocial> wrapper = new LambdaQueryWrapper<>();
        wrapper.eq(UserSocial::getAccountId, accountId);
        return this.baseMapper.selectOne(wrapper);
    }
}
