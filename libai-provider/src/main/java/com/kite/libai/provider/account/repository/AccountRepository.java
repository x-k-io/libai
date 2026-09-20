package com.kite.libai.provider.account.repository;

import com.kite.libai.core.repository.BaseRepository;
import com.kite.libai.provider.account.model.entity.Account;

import java.util.List;

public interface AccountRepository extends BaseRepository<Account> {
    Account getAccountByMobile(String mobile);

    List<Account> getAccountByNickname(String nickname);

    List<Account> getValidAccounts(int pageNum, int pageSize);

    boolean updateStatus(Long id, String status);

    boolean updatePassword(Long id, String newPassword);

    List<Account> getAccountList(String type, String nickname, String mobile);
}
