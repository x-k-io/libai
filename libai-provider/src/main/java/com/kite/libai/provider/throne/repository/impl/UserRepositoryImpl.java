package com.kite.libai.provider.throne.repository.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.conditions.update.LambdaUpdateWrapper;
import com.kite.libai.boot.repository.MybatisBaseRepository;
import com.kite.libai.core.result.PageResult;

import org.apache.commons.lang3.StringUtils;
import com.kite.libai.provider.throne.mapper.UserMapper;
import com.kite.libai.provider.throne.model.entity.User;
import com.kite.libai.provider.throne.repository.UserRepository;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;

@Repository
public class UserRepositoryImpl extends MybatisBaseRepository<UserMapper, User> implements UserRepository {
    @Override
    public PageResult<User> pageGet(String name, Integer pageNum, Integer pageSize) {
        LambdaQueryWrapper<User> queryWrapper = new LambdaQueryWrapper<>();
        if (StringUtils.isNotBlank(name)) {
            queryWrapper.like(User::getName, name);
        }
        return this.pageGet(queryWrapper, pageNum, pageSize);
    }

    @Override
    public User getUserByUsername(String username) {
        LambdaQueryWrapper<User> queryWrapper = new LambdaQueryWrapper<>();
        queryWrapper.eq(User::getUsername, username);
        return this.baseMapper.selectOne(queryWrapper);
    }

    @Override
    public boolean updateStatus(Long id, String status) {
        LambdaUpdateWrapper<User> updateWrapper = new LambdaUpdateWrapper<>();
        updateWrapper.set(User::getStatus, status);
        updateWrapper.set(User::getUpdateTime, LocalDateTime.now());
        updateWrapper.eq(User::getId, id);
        return this.update(updateWrapper);
    }
}
