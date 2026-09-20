package com.kite.libai.provider.throne.repository.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.kite.libai.core.repository.MybatisBaseRepository;
import com.kite.libai.common.result.PageResult;

import org.apache.commons.lang3.StringUtils;
import com.kite.libai.provider.throne.mapper.PermissionMapper;
import com.kite.libai.provider.throne.model.entity.Permission;
import com.kite.libai.provider.throne.repository.PermissionRepository;
import org.springframework.stereotype.Repository;

@Repository
public class PermissionRepositoryImpl extends MybatisBaseRepository<PermissionMapper, Permission> implements PermissionRepository {
    @Override
    public Permission getByPermission(String permission) {
        LambdaQueryWrapper<Permission> queryWrapper = new LambdaQueryWrapper<>();
        queryWrapper.eq(Permission::getPermission, permission);
        return this.baseMapper.selectOne(queryWrapper);
    }

    @Override
    public PageResult<Permission> pageGet(String name, Integer pageNum, Integer pageSize) {
        LambdaQueryWrapper<Permission> queryWrapper = new LambdaQueryWrapper<>();
        if (StringUtils.isNotBlank(name)) {
            queryWrapper.like(Permission::getName, name);
        }
        return this.pageGet(queryWrapper, pageNum, pageSize);
    }
}
