package com.kite.libai.provider.throne.repository.impl;

import com.kite.libai.core.repository.MybatisBaseRepository;
import com.kite.libai.provider.throne.mapper.UserLoginLogMapper;
import com.kite.libai.provider.throne.model.entity.UserLoginLog;
import com.kite.libai.provider.throne.repository.UserLoginLogRepository;
import org.springframework.stereotype.Repository;

@Repository
public class UserLoginLogRepositoryImpl extends MybatisBaseRepository<UserLoginLogMapper, UserLoginLog> implements UserLoginLogRepository {
}
