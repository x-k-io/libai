package com.kite.libai.provider.throne.service;

import com.kite.libai.core.repository.BaseService;
import com.kite.libai.common.result.PageResult;
import com.kite.libai.provider.throne.model.entity.Role;
import com.kite.libai.provider.throne.repository.RoleRepository;
import org.springframework.stereotype.Service;

import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;

import java.util.List;

@Slf4j
@Service
@AllArgsConstructor
public class RoleService extends BaseService<RoleRepository, Role> {

    public PageResult<Role> pageGet(String name, Integer pageNum, Integer pageSize) {
        return this.repository.pageGet(name, pageNum, pageSize);
    }

    public Role getRoleByCode(String code) {
        return this.repository.getRoleByCode(code);
    }

    public List<Role> getRoleList() {
        return this.repository.batchGet();
    }
}
