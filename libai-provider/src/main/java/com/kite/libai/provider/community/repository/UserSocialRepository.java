package com.kite.libai.provider.community.repository;

import com.kite.libai.boot.repository.BaseRepository;
import com.kite.libai.provider.community.model.entity.UserSocial;

import java.util.List;

public interface UserSocialRepository extends BaseRepository<UserSocial> {
    List<UserSocial> batchGetByAccountId(List<Long> accountIds);

    UserSocial getByAccountId(Long accountId);
}
