package com.kite.libai.provider.throne.repository.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.kite.libai.core.repository.MybatisBaseRepository;
import com.kite.libai.provider.throne.mapper.UserRoleMapper;
import com.kite.libai.provider.throne.model.entity.UserRole;
import com.kite.libai.provider.throne.repository.UserRoleRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class UserRoleRepositoryImpl extends MybatisBaseRepository<UserRoleMapper, UserRole> implements UserRoleRepository {

    @Override
    public List<UserRole> getByUserId(Long userId) {
        LambdaQueryWrapper<UserRole> queryWrapper = new LambdaQueryWrapper<>();
        queryWrapper.eq(UserRole::getUserId, userId);
        return this.baseMapper.selectList(queryWrapper);
    }

    @Override
    public List<UserRole> getByRoleId(Long roleId) {
        LambdaQueryWrapper<UserRole> queryWrapper = new LambdaQueryWrapper<>();
        queryWrapper.eq(UserRole::getRoleId, roleId);
        return this.baseMapper.selectList(queryWrapper);
    }

    @Override
    public boolean hasRole(Long userId, Long roleId) {
        LambdaQueryWrapper<UserRole> queryWrapper = new LambdaQueryWrapper<>();
        queryWrapper.eq(UserRole::getUserId, userId)
                .eq(UserRole::getRoleId, roleId);
        return this.baseMapper.selectCount(queryWrapper) > 0;
    }

    @Override
    public void deleteByUserId(Long userId) {
        LambdaQueryWrapper<UserRole> queryWrapper = new LambdaQueryWrapper<>();
        queryWrapper.eq(UserRole::getUserId, userId);
        this.baseMapper.delete(queryWrapper);
    }

    @Override
    public void deleteByRoleId(Long roleId) {
        LambdaQueryWrapper<UserRole> queryWrapper = new LambdaQueryWrapper<>();
        queryWrapper.eq(UserRole::getRoleId, roleId);
        this.baseMapper.delete(queryWrapper);
    }

    @Override
    public boolean delete(Long userId, Long roleId) {
        LambdaQueryWrapper<UserRole> queryWrapper = new LambdaQueryWrapper<>();
        queryWrapper.eq(UserRole::getUserId, userId)
                .eq(UserRole::getRoleId, roleId);
        return this.baseMapper.delete(queryWrapper) > 0;
    }
}
