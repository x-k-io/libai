package com.kite.libai.provider.throne.repository;

import com.kite.libai.boot.repository.BaseRepository;
import com.kite.libai.core.result.PageResult;
import com.kite.libai.provider.throne.model.entity.Permission;

public interface PermissionRepository extends BaseRepository<Permission> {
    Permission getByPermission(String permission);

    PageResult<Permission> pageGet(String name, Integer pageNum, Integer pageSize);
}
