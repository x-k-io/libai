package com.kite.libai.provider.throne.repository;

import com.kite.libai.core.repository.BaseRepository;
import com.kite.libai.common.result.PageResult;
import com.kite.libai.provider.throne.model.entity.Role;

public interface RoleRepository extends BaseRepository<Role> {


    PageResult<Role> pageGet(String name, Integer pageNum, Integer pageSize);

    Role getRoleByCode(String code);
}
