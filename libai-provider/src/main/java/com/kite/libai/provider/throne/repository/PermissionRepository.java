package com.kite.libai.provider.throne.repository;

import com.kite.libai.core.repository.BaseRepository;
import com.kite.libai.common.result.PageResult;
import com.kite.libai.provider.throne.model.entity.Permission;

public interface PermissionRepository extends BaseRepository<Permission> {
    Permission getByPermission(String permission);

    PageResult<Permission> pageGet(String name, Integer pageNum, Integer pageSize);
}
