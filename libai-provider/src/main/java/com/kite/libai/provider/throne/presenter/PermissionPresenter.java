package com.kite.libai.provider.throne.presenter;

import java.time.LocalDateTime;
import java.util.List;

import com.kite.libai.common.result.PageResult;
import com.kite.libai.common.utils.BeanUtils;
import com.kite.libai.provider.throne.model.entity.Permission;
import com.kite.libai.provider.throne.model.request.CreatePermissionRequest;
import com.kite.libai.provider.throne.model.request.UpdatePermissionRequest;
import com.kite.libai.provider.throne.model.vo.PermissionVo;
import com.kite.libai.provider.throne.service.PermissionService;
import com.kite.libai.provider.throne.service.RolePermissionService;

import org.springframework.stereotype.Component;

import com.kite.libai.common.exception.ServiceException;

import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Component
@AllArgsConstructor
public class PermissionPresenter {

    private final PermissionService permissionService;

    private final RolePermissionService rolePermissionService;

    public boolean create(CreatePermissionRequest request) {
        Permission check = permissionService.getPermissionByPermission(request.getPermission());
        if (check != null) {
            throw new ServiceException( "权限标识已存在");
        }
        Permission permission = BeanUtils.copy(request, Permission.class);
        permission.setUpdateTime(LocalDateTime.now());
        permission.setCreateTime(LocalDateTime.now());
        return permissionService.insert(permission);

    }

    public boolean updateById(UpdatePermissionRequest request) {
        Permission permission = permissionService.getById(request.getId());
        if (permission == null) {
            throw new ServiceException("权限不存在");
        }
        permission.setName(request.getName());
        permission.setOrders(request.getOrders());
        permission.setUpdateTime(LocalDateTime.now());
        return permissionService.updateById(permission);
    }

    public boolean deleteById(Long id) {
        Permission permission = permissionService.getById(id);
        if (permission == null) {
            throw new ServiceException("权限不存在");
        }
        rolePermissionService.deleteByPermissionId(id);
        return permissionService.deleteById(id);
    }

    public PermissionVo getById(Long id) {
        Permission permission = permissionService.getById(id);
        if (permission == null) {
            throw new ServiceException("权限不存在");
        }
        return BeanUtils.copy(permission, PermissionVo.class);
    }

    public List<PermissionVo> getPermissionList() {
        List<Permission> permissions = permissionService.getPermissionList();
        return BeanUtils.copy(permissions, PermissionVo.class);
    }

    public PageResult<PermissionVo> pageGet(String name, Integer pageNum, Integer pageSize) {
        PageResult<Permission> pageResult = permissionService.pageGet(name, pageNum, pageSize);
        List<PermissionVo> permissionVos = BeanUtils.copy(pageResult.getRecords(), PermissionVo.class);
        return PageResult.success(permissionVos, pageResult.getPageCount(), pageResult.getTotal());
    }
}
