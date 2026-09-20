package com.kite.libai.provider.throne.service;

import java.util.List;
import java.util.stream.Collectors;

import com.kite.libai.core.repository.BaseService;
import com.kite.libai.provider.throne.model.entity.UserRole;
import com.kite.libai.provider.throne.repository.UserRoleRepository;
import org.springframework.stereotype.Service;

import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
@AllArgsConstructor
public class UserRoleService extends BaseService<UserRoleRepository, UserRole> {


    public List<UserRole> getByUserId(Long userId) {
        return this.repository.getByUserId(userId);
    }

    public List<UserRole> getByRoleId(Long roleId) {
        return this.repository.getByRoleId(roleId);
    }

    public List<Long> getUserByRoleId(Long roleId) {
        List<UserRole> userRoles = this.repository.getByRoleId(roleId);
        return userRoles.stream().map(UserRole::getUserId).collect(Collectors.toList());
    }

    public boolean hasRole(Long userId, Long roleId) {
        return this.repository.hasRole(userId, roleId);
    }

    public void deleteByUserId(Long userId) {
        this.repository.deleteByUserId(userId);
    }

    public void deleteByRoleId(Long roleId) {
        this.repository.deleteByRoleId(roleId);
    }
}
