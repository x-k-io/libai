package com.kite.libai.provider.throne.repository;

import com.kite.libai.boot.repository.BaseRepository;
import com.kite.libai.core.result.PageResult;
import com.kite.libai.provider.throne.model.entity.User;

public interface UserRepository extends BaseRepository<User> {

    PageResult<User> pageGet(String name, Integer pageNum, Integer pageSize);

    User getUserByUsername(String loginName);

    boolean updateStatus(Long id, String status);
}
