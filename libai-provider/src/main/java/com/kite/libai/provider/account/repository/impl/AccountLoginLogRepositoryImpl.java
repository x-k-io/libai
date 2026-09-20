package com.kite.libai.provider.account.repository.impl;

import com.kite.libai.core.repository.MybatisBaseRepository;
import com.kite.libai.provider.account.mapper.AccountLoginLogMapper;
import com.kite.libai.provider.account.model.entity.AccountLoginLog;
import com.kite.libai.provider.account.repository.AccountLoginLogRepository;
import org.springframework.stereotype.Repository;

@Repository
public class AccountLoginLogRepositoryImpl extends MybatisBaseRepository<AccountLoginLogMapper, AccountLoginLog> implements AccountLoginLogRepository {
}
