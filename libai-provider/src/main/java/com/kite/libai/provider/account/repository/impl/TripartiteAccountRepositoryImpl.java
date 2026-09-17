package com.kite.libai.provider.account.repository.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.kite.libai.boot.repository.MybatisBaseRepository;

import org.apache.commons.lang3.StringUtils;
import com.kite.libai.provider.account.mapper.TripartiteAccountMapper;
import com.kite.libai.provider.account.model.entity.TripartiteAccount;
import com.kite.libai.provider.account.repository.TripartiteAccountRepository;
import org.springframework.stereotype.Repository;

@Repository
public class TripartiteAccountRepositoryImpl extends MybatisBaseRepository<TripartiteAccountMapper, TripartiteAccount> implements TripartiteAccountRepository {
    @Override
    public TripartiteAccount getTripartiteAccount(String appId, String openId, String unionId, Long accountId) {
        LambdaQueryWrapper<TripartiteAccount> wrapper = new LambdaQueryWrapper<>();
        wrapper.eq(TripartiteAccount::getAppId, appId);
        if (StringUtils.isNotBlank(unionId)) {
            wrapper.eq(TripartiteAccount::getUnionId, unionId);
        }
        if (StringUtils.isNotBlank(openId)) {
            wrapper.eq(TripartiteAccount::getOpenId, openId);
        }
        if (null != accountId) {
            wrapper.eq(TripartiteAccount::getAccountId, accountId);
        }
        return this.baseMapper.selectOne(wrapper);
    }
}
