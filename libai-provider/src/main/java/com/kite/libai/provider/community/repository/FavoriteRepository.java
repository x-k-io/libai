package com.kite.libai.provider.community.repository;

import com.kite.libai.core.repository.BaseRepository;
import com.kite.libai.common.result.PageResult;
import com.kite.libai.provider.community.model.entity.Favorite;

import java.util.List;

public interface FavoriteRepository extends BaseRepository<Favorite> {
    Favorite getByAccountIdAndEntryId(Long accountId, Long entryId);

    List<Favorite> getByAccountIdAndEntryIds(Long accountId, List<Long> entryIds);

    PageResult<Favorite> pageGetFavoriteByAccountId(Long accountId, int pageNum, int pageSize);
}
