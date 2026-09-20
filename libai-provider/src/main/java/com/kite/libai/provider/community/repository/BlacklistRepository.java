package com.kite.libai.provider.community.repository;

import com.kite.libai.core.repository.BaseRepository;
import com.kite.libai.provider.community.model.entity.Blacklist;

import java.util.List;

public interface BlacklistRepository extends BaseRepository<Blacklist> {

    Blacklist getBlacklist(Long accountId, Long theirId);

    List<Blacklist> getBlacklistByAccountId(Long accountId);
}
