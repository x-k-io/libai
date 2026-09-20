package com.kite.libai.provider.throne.presenter;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

import com.kite.libai.common.result.PageResult;
import com.kite.libai.common.utils.BeanUtils;
import com.kite.libai.provider.throne.model.entity.Permission;
import com.kite.libai.provider.throne.model.entity.Role;
import com.kite.libai.provider.throne.model.entity.RolePermission;
import com.kite.libai.provider.throne.model.request.CreateRoleRequest;
import com.kite.libai.provider.throne.model.request.UpdateRolePermissionRequest;
import com.kite.libai.provider.throne.model.request.UpdateRoleRequest;
import com.kite.libai.provider.throne.model.vo.RoleVo;
import com.kite.libai.provider.throne.service.PermissionService;
import com.kite.libai.provider.throne.service.UserRoleService;
import com.kite.libai.provider.throne.service.RolePermissionService;
import com.kite.libai.provider.throne.service.RoleService;
import org.apache.commons.collections4.CollectionUtils;

import org.springframework.stereotype.Component;

import com.kite.libai.common.exception.ServiceException;
import com.kite.libai.common.result.SystemCode;

import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Component
@AllArgsConstructor
public class RolePresenter {

    private final RoleService roleService;

    private final PermissionService permissionService;

    private final UserRoleService userRoleService;

    private final RolePermissionService rolePermissionService;

    public boolean create(CreateRoleRequest request) {
        Role check = roleService.getRoleByCode(request.getCode());
        if (check != null) {
            throw new ServiceException( "角色编码已存在");
        }
        Role role = new Role();
        role.setCode(request.getCode());
        role.setName(request.getName());
        role.setDescription(request.getDescription());
        role.setCreateTime(LocalDateTime.now());
        role.setUpdateTime(LocalDateTime.now());
        return roleService.save(role);
    }

    public boolean updateById(UpdateRoleRequest request) {
        Role role = roleService.getById(request.getId());
        if (role == null) {
            throw new ServiceException("未查询到角色");
        }
        role.setName(request.getName());
        role.setDescription(request.getDescription());
        role.setUpdateTime(LocalDateTime.now());
        return roleService.updateById(role);
    }

    public boolean deleteById(Long id) {
        Role role = roleService.getById(id);
        if (role == null) {
            return Boolean.TRUE;
        }
        // 删除账号和角色关系
        userRoleService.deleteByRoleId(role.getId());
        // 删除角色和权限的关联关系
        rolePermissionService.deleteByRoleId(role.getId());
        // 删除角色
        return roleService.deleteById(id);
    }

    public RoleVo getById(Long id) {
        Role role = roleService.getById(id);
        if (role == null) {
            throw new ServiceException("未查询到角色");
        }
        return BeanUtils.copy(role, RoleVo.class);
    }

    public List<RoleVo> getRoleList() {
        List<Role> roles = roleService.getRoleList();
        return BeanUtils.copy(roles, RoleVo.class);
    }


    public List<Long> getPermissionByRoleId(Long roleId) {
        List<RolePermission> rolePermissions = rolePermissionService.getByRoleId(roleId);
        return rolePermissions.stream().map(RolePermission::getPermissionId).collect(Collectors.toList());
    }

    public boolean updateRolePermission(UpdateRolePermissionRequest request) {
        Long roleId = request.getRoleId();
        List<Long> permissionIds = request.getPermissionIds();
        if (roleId == null) {
            throw new ServiceException(SystemCode.PARAM_MISS, "roleId 不能为空");
        }
        if (CollectionUtils.isEmpty(permissionIds)) {
            rolePermissionService.deleteByRoleId(roleId);
            return Boolean.TRUE;
        }

        // 验证权限是否存在
        List<Permission> permissions = permissionService.batchGetByIds(permissionIds);
        if (permissions.size() != permissionIds.size()) {
            throw new ServiceException("添加的权限不存在");
        }
        List<RolePermission> rolePermissions = rolePermissionService.getByRoleId(roleId);
        // 已存在的权限
        List<Long> existPermissionIds = rolePermissions.stream().map(RolePermission::getPermissionId).collect(Collectors.toList());
        // 需要删除的权限
        List<Long> needDeleteIds = rolePermissions.stream().filter(x -> !permissionIds.contains(x.getPermissionId())).map(RolePermission::getId).collect(Collectors.toList());
        // 需要添加的权限
        List<Long> needAddPermissionIds = permissionIds.stream().filter(permissionId -> !existPermissionIds.contains(permissionId)).collect(Collectors.toList());
        rolePermissionService.deleteByIds(needDeleteIds);
        List<RolePermission> newRolePermissions = needAddPermissionIds.stream().map(permissionId -> {
            RolePermission rolePermission = new RolePermission();
            rolePermission.setRoleId(roleId);
            rolePermission.setPermissionId(permissionId);
            rolePermission.setCreateTime(LocalDateTime.now());
            return rolePermission;
        }).collect(Collectors.toList());
        return rolePermissionService.saveBatch(newRolePermissions);
    }

    public PageResult<RoleVo> pageGet(String name, Integer pageNum, Integer pageSize) {
        PageResult<Role> pageResult = roleService.pageGet(name, pageNum, pageSize);
        List<Role> roles = pageResult.getRecords();
        List<RoleVo> roleVos = BeanUtils.copy(roles, RoleVo.class);
        return PageResult.success(roleVos, pageResult.getPageCount(), pageResult.getTotal());
    }
}
