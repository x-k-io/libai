package com.kite.libai.provider.account.repository.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.kite.libai.boot.repository.MybatisBaseRepository;
import com.kite.libai.provider.account.mapper.AccountRefreshTokenMapper;
import com.kite.libai.provider.account.model.entity.AccountRefreshToken;
import com.kite.libai.provider.account.repository.AccountRefreshTokenRepository;
import org.springframework.stereotype.Repository;

@Repository
public class AccountRefreshTokenRepositoryImpl extends MybatisBaseRepository<AccountRefreshTokenMapper, AccountRefreshToken> implements AccountRefreshTokenRepository {
    @Override
    public AccountRefreshToken getByRefreshToken(String refreshToken) {
        LambdaQueryWrapper<AccountRefreshToken> queryWrapper = new LambdaQueryWrapper<>();
        queryWrapper.eq(AccountRefreshToken::getRefreshToken, refreshToken);
        return this.baseMapper.selectOne(queryWrapper);
    }
}
