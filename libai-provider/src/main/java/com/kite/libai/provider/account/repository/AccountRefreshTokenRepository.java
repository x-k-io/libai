package com.kite.libai.provider.account.repository;

import com.kite.libai.boot.repository.BaseRepository;
import com.kite.libai.provider.account.model.entity.AccountRefreshToken;

public interface AccountRefreshTokenRepository extends BaseRepository<AccountRefreshToken> {
    AccountRefreshToken getByRefreshToken(String refreshToken);
}
