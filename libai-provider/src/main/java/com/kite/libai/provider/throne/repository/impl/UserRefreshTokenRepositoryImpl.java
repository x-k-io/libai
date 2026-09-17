package com.kite.libai.provider.throne.repository.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.kite.libai.boot.repository.MybatisBaseRepository;
import com.kite.libai.provider.throne.mapper.UserRefreshTokenMapper;
import com.kite.libai.provider.throne.model.entity.UserRefreshToken;
import com.kite.libai.provider.throne.repository.UserRefreshTokenRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class UserRefreshTokenRepositoryImpl extends MybatisBaseRepository<UserRefreshTokenMapper, UserRefreshToken> implements UserRefreshTokenRepository {
    @Override
    public UserRefreshToken getByRefreshToken(String refreshToken) {
        LambdaQueryWrapper<UserRefreshToken> queryWrapper = new LambdaQueryWrapper<>();
        queryWrapper.eq(UserRefreshToken::getRefreshToken, refreshToken);
        return this.baseMapper.selectOne(queryWrapper);
    }

    @Override
    public List<UserRefreshToken> getByUserId(Long userId) {
        LambdaQueryWrapper<UserRefreshToken> queryWrapper = new LambdaQueryWrapper<>();
        queryWrapper.eq(UserRefreshToken::getUserId, userId);
        return this.baseMapper.selectList(queryWrapper);
    }
}
