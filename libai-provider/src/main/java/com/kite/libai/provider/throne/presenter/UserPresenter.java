package com.kite.libai.provider.throne.presenter;

import com.kite.libai.common.enums.UserType;
import com.kite.libai.common.model.KiteAccount;
import com.kite.libai.common.result.PageResult;
import com.kite.libai.common.utils.BeanUtils;
import com.kite.libai.common.utils.DesensitizationUtils;
import com.kite.libai.provider.account.enums.AccountStatus;
import com.kite.libai.provider.throne.service.UserRefreshTokenService;
import com.kite.libai.provider.throne.model.request.UpdatePasswordRequest;
import com.kite.libai.provider.throne.model.request.UpdateUserRequest;
import com.kite.libai.provider.throne.model.entity.Permission;
import com.kite.libai.provider.throne.model.entity.RolePermission;
import com.kite.libai.provider.throne.model.entity.User;
import com.kite.libai.provider.throne.model.entity.UserRole;
import com.kite.libai.provider.throne.model.entity.Role;
import com.kite.libai.provider.throne.model.request.CreateUserRequest;
import com.kite.libai.provider.throne.model.request.UpdateUserRoleRequest;
import com.kite.libai.provider.throne.model.request.UpdateUserStatusRequest;
import com.kite.libai.provider.throne.model.vo.UserVo;
import com.kite.libai.provider.throne.service.PermissionService;
import com.kite.libai.provider.throne.service.RolePermissionService;
import com.kite.libai.provider.throne.service.UserRoleService;
import com.kite.libai.provider.throne.service.RoleService;
import com.kite.libai.provider.throne.service.UserService;
import org.apache.commons.collections4.CollectionUtils;
import org.springframework.stereotype.Component;

import com.kite.libai.common.exception.ServiceException;
import com.kite.libai.common.result.SystemCode;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Component
@AllArgsConstructor
public class UserPresenter {

    private final UserRefreshTokenService userRefreshTokenService;

    private final UserService userService;

    private final RoleService roleService;

    private final UserRoleService userRoleService;

    private final RolePermissionService rolePermissionService;

    private final PermissionService permissionService;

    public KiteAccount getKiteAccount(Long accountId) {
        User user = userService.getById(accountId);
        if (user == null) {
            return null;
        }
        // 封装 KiteAccount
        KiteAccount kiteAccount = BeanUtils.copy(user, KiteAccount.class);
        kiteAccount.setType(UserType.USER.getType());
        kiteAccount.setMobile(DesensitizationUtils.mobileNo(user.getMobile()));
        List<String> permissions = getUserPermissions(user.getId());
        kiteAccount.setPermissions(permissions);
        return kiteAccount;
    }

    public boolean create(CreateUserRequest request) {
        return userService.create(request);
    }

    public boolean updateById(UpdateUserRequest request) {
        return userService.update(request);
    }

    public boolean resetPassword(Long id) {
        return userService.resetPassword(id);
    }


    public boolean updatePassword(UpdatePasswordRequest request) {
        return userService.updatePassword(request);
    }


    public boolean updateStatus(UpdateUserStatusRequest request) {
        Long userId = request.getUserId();
        String status = request.getStatus();
        boolean result = userService.updateStatus(userId, status);
        if (AccountStatus.LOCKED.getStatus().equals(status) && result) {
            userRefreshTokenService.clearSessionByUserId(userId);
        }
        return result;
    }

    /**
     * 删除账号信息
     *
     * @param id id
     * @return boolean
     */
    public boolean deleteById(Long id) {
        return userService.deleteById(id);
    }

    /**
     * 查询账号信息详情
     *
     * @param id id
     * @return CarBrand
     */
    public User getById(Long id) {
        return userService.getById(id);
    }

    public List<String> getUserPermissions(Long userId) {
        List<UserRole> userRoles = userRoleService.getByUserId(userId);
        List<Long> roleIds = userRoles.stream().map(UserRole::getRoleId).collect(Collectors.toList());
        if (CollectionUtils.isEmpty(roleIds)) {
            return new ArrayList<>();
        }
        List<RolePermission> rolePermissions = rolePermissionService.getByRoleIds(roleIds);
        List<Long> permissionIds = rolePermissions.stream().map(RolePermission::getPermissionId).distinct().collect(Collectors.toList());
        if (CollectionUtils.isEmpty(permissionIds)) {
            return new ArrayList<>();
        }
        List<Permission> permissions = permissionService.batchGetByIds(permissionIds);
        return permissions.stream().map(Permission::getPermission).distinct().collect(Collectors.toList());
    }

    public List<Long> getRoleByUserId(Long userId) {
        List<UserRole> userRoles = userRoleService.getByUserId(userId);
        return userRoles.stream().map(UserRole::getRoleId).collect(Collectors.toList());
    }

    public boolean updateAccountRole(UpdateUserRoleRequest request) {
        Long userId = request.getUserId();
        List<Long> roleIds = request.getRoleIds();
        // 参数校验
        if (userId == null) {
            throw new ServiceException(SystemCode.PARAM_MISS, "userId 不能为空");
        }
        // 如果角色列表为空，直接删除所有角色关联
        if (CollectionUtils.isEmpty(roleIds)) {
            userRoleService.deleteByUserId(request.getUserId());
            return Boolean.TRUE;
        }
        // 验证角色是否存在
        List<Role> roles = roleService.batchGetByIds(roleIds);
        if (roles.size() != roleIds.size()) {
            throw new ServiceException( "添加的角色不存在");
        }
        List<UserRole> userRoles = userRoleService.getByUserId(userId);
        List<Long> existRoleIds = userRoles.stream().map(UserRole::getRoleId).collect(Collectors.toList());
        // 需要删除的角色
        List<Long> needDeleteIds = userRoles.stream().filter(x -> !roleIds.contains(x.getRoleId())).map(UserRole::getId).collect(Collectors.toList());
        // 需要添加的角色
        List<Long> needAddRoleIds = roleIds.stream().filter(roleId -> !existRoleIds.contains(roleId)).collect(Collectors.toList());
        userRoleService.deleteByIds(needDeleteIds);
        List<UserRole> newUserRoles = needAddRoleIds.stream().map(roleId -> {
            UserRole accountRole = new UserRole();
            accountRole.setUserId(userId);
            accountRole.setRoleId(roleId);
            accountRole.setCreateTime(LocalDateTime.now());
            return accountRole;
        }).collect(Collectors.toList());
        return userRoleService.insert(newUserRoles);
    }

    public PageResult<UserVo> pageGet(String name, Integer pageNum, Integer pageSize) {
        PageResult<User> pageResult = userService.pageGet(name, pageNum, pageSize);
        List<UserVo> userVos = BeanUtils.copy(pageResult.getRecords(), UserVo.class);
        return PageResult.success(userVos, pageResult.getPageCount(), pageResult.getTotal());
    }
}
