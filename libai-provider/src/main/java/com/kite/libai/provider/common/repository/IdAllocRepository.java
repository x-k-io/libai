package com.kite.libai.provider.common.repository;

import com.kite.libai.boot.repository.BaseRepository;
import com.kite.libai.provider.common.model.entity.IdAlloc;

public interface IdAllocRepository extends BaseRepository<IdAlloc> {
    IdAlloc getByBizType(String bizType);

    boolean update(String bizType, Long maxId, Long version);
}
