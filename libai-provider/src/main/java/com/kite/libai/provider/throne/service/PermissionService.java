package com.kite.libai.provider.throne.service;

import com.kite.libai.core.repository.BaseService;
import com.kite.libai.common.result.PageResult;
import com.kite.libai.provider.throne.model.entity.Permission;
import com.kite.libai.provider.throne.repository.PermissionRepository;
import org.springframework.stereotype.Service;

import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;

import java.util.List;

@Slf4j
@Service
@AllArgsConstructor
public class PermissionService extends BaseService<PermissionRepository, Permission> {

    public PageResult<Permission> pageGet(String name, Integer pageNum, Integer pageSize) {
        return this.repository.pageGet(name, pageNum, pageSize);
    }

    public Permission getPermissionByPermission(String permission) {
        return this.repository.getByPermission(permission);
    }

    public List<Permission> getPermissionList() {
        return this.repository.batchGet();
    }
}
