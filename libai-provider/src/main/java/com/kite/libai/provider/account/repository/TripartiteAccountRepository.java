package com.kite.libai.provider.account.repository;

import com.kite.libai.core.repository.BaseRepository;
import com.kite.libai.provider.account.model.entity.TripartiteAccount;

public interface TripartiteAccountRepository extends BaseRepository<TripartiteAccount> {
    TripartiteAccount getTripartiteAccount(String appId, String openId, String unionId, Long accountId);
}
