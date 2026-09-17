package com.kite.libai.provider.throne.repository.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.kite.libai.boot.repository.MybatisBaseRepository;
import com.kite.libai.core.result.PageResult;

import org.apache.commons.lang3.StringUtils;
import com.kite.libai.provider.throne.mapper.RoleMapper;
import com.kite.libai.provider.throne.model.entity.Role;
import com.kite.libai.provider.throne.repository.RoleRepository;
import org.springframework.stereotype.Repository;

@Repository
public class RoleRepositoryImpl extends MybatisBaseRepository<RoleMapper, Role> implements RoleRepository {

    @Override
    public PageResult<Role> pageGet(String name, Integer pageNum, Integer pageSize) {
        LambdaQueryWrapper<Role> queryWrapper = new LambdaQueryWrapper<>();
        if (StringUtils.isNotBlank(name)) {
            queryWrapper.like(Role::getName, name);
        }
        return this.pageGet(queryWrapper, pageNum, pageSize);
    }

    @Override
    public Role getRoleByCode(String code) {
        LambdaQueryWrapper<Role> queryWrapper = new LambdaQueryWrapper<>();
        queryWrapper.eq(Role::getCode, code);
        return this.baseMapper.selectOne(queryWrapper);
    }
}
