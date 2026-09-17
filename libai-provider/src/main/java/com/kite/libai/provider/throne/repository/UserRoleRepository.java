package com.kite.libai.provider.throne.repository;

import com.kite.libai.boot.repository.BaseRepository;
import com.kite.libai.provider.throne.model.entity.UserRole;

import java.util.List;

public interface UserRoleRepository extends BaseRepository<UserRole> {

    List<UserRole> getByUserId(Long userId);

    List<UserRole> getByRoleId(Long roleId);

    boolean hasRole(Long userId, Long roleId);

    void deleteByUserId(Long userId);

    void deleteByRoleId(Long roleId);

    boolean delete(Long userId, Long roleId);
}
