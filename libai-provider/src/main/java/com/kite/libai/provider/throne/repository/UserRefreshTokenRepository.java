package com.kite.libai.provider.throne.repository;

import com.kite.libai.boot.repository.BaseRepository;
import com.kite.libai.provider.throne.model.entity.UserRefreshToken;

import java.util.List;

public interface UserRefreshTokenRepository extends BaseRepository<UserRefreshToken> {
    UserRefreshToken getByRefreshToken(String refreshToken);

    List<UserRefreshToken> getByUserId(Long userId);
}
