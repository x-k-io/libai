package com.kite.libai.provider.community.repository;

import com.kite.libai.boot.repository.BaseRepository;
import com.kite.libai.core.result.PageResult;
import com.kite.libai.provider.community.model.entity.Browse;


public interface BrowseRepository extends BaseRepository<Browse> {

    PageResult<Browse> pageGetBrowseByAccountId(Long accountId, int pageNum, int pageSize);
}
