package com.kite.libai.provider.throne.service;

import java.util.List;

import com.kite.libai.core.repository.BaseService;
import com.kite.libai.provider.throne.model.entity.RolePermission;
import com.kite.libai.provider.throne.repository.RolePermissionRepository;
import org.springframework.stereotype.Service;

import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
@AllArgsConstructor
public class RolePermissionService extends BaseService<RolePermissionRepository, RolePermission> {

    public List<RolePermission> getByRoleId(Long roleId) {
        return this.repository.getByRoleId(roleId);
    }
    public List<RolePermission> getByRoleIds(List<Long> roleIds){
        return this.repository.getByRoleIds(roleIds);
    }

    public boolean hasPermission(Long roleId, Long permissionId) {
        return this.repository.hasPermission(roleId, permissionId);
    }

    public void deleteByRoleId(Long roleId) {
        this.repository.deleteByRoleId(roleId);
    }

    public void deleteByPermissionId(Long permissionId) {
        this.repository.deleteByPermissionId(permissionId);
    }

    public boolean delete(Long roleId, Long permissionId) {
        return this.repository.delete(roleId, permissionId);
    }
}
