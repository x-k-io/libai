package com.kite.libai.provider.account.repository;

import com.kite.libai.core.repository.BaseRepository;
import com.kite.libai.provider.account.model.entity.TripartiteApp;

public interface TripartiteAppRepository extends BaseRepository<TripartiteApp> {
    TripartiteApp getByAppCode(String appCode);
}
