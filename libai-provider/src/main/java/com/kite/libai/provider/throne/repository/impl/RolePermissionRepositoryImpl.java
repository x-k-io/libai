package com.kite.libai.provider.throne.repository.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.kite.libai.boot.repository.MybatisBaseRepository;
import com.kite.libai.provider.throne.mapper.RolePermissionMapper;
import com.kite.libai.provider.throne.model.entity.RolePermission;
import com.kite.libai.provider.throne.repository.RolePermissionRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class RolePermissionRepositoryImpl extends MybatisBaseRepository<RolePermissionMapper, RolePermission> implements RolePermissionRepository {

    @Override
    public List<RolePermission> getByRoleIds(List<Long> roleIds) {
        LambdaQueryWrapper<RolePermission> queryWrapper = new LambdaQueryWrapper<>();
        queryWrapper.in(RolePermission::getRoleId, roleIds);
        return this.baseMapper.selectList(queryWrapper);
    }

    @Override
    public List<RolePermission> getByRoleId(Long roleId) {
        LambdaQueryWrapper<RolePermission> queryWrapper = new LambdaQueryWrapper<>();
        queryWrapper.eq(RolePermission::getRoleId, roleId);
        return this.baseMapper.selectList(queryWrapper);
    }

    @Override
    public boolean hasPermission(Long roleId, Long permissionId) {
        LambdaQueryWrapper<RolePermission> queryWrapper = new LambdaQueryWrapper<>();
        queryWrapper.eq(RolePermission::getRoleId, roleId)
                .eq(RolePermission::getPermissionId, permissionId);
        return this.baseMapper.selectCount(queryWrapper) > 0;
    }

    @Override
    public void deleteByRoleId(Long roleId) {
        LambdaQueryWrapper<RolePermission> queryWrapper = new LambdaQueryWrapper<>();
        queryWrapper.eq(RolePermission::getRoleId, roleId);
        this.baseMapper.delete(queryWrapper);
    }

    @Override
    public void deleteByPermissionId(Long permissionId) {
        LambdaQueryWrapper<RolePermission> queryWrapper = new LambdaQueryWrapper<>();
        queryWrapper.eq(RolePermission::getPermissionId, permissionId);
        this.baseMapper.delete(queryWrapper);
    }

    @Override
    public boolean delete(Long roleId, Long permissionId) {
        LambdaQueryWrapper<RolePermission> queryWrapper = new LambdaQueryWrapper<>();
        queryWrapper.eq(RolePermission::getRoleId, roleId)
                .eq(RolePermission::getPermissionId, permissionId);
        return this.baseMapper.delete(queryWrapper) > 0;
    }
}
