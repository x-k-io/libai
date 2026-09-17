package com.kite.libai.provider.throne.repository;

import com.kite.libai.boot.repository.BaseRepository;
import com.kite.libai.provider.throne.model.entity.RolePermission;

import java.util.List;

public interface RolePermissionRepository extends BaseRepository<RolePermission> {
    List<RolePermission> getByRoleIds(List<Long> roleIds);

    List<RolePermission> getByRoleId(Long roleId);

    boolean hasPermission(Long roleId, Long permissionId);

    void deleteByRoleId(Long roleId);

    void deleteByPermissionId(Long permissionId);

    boolean delete(Long roleId, Long permissionId);

}
