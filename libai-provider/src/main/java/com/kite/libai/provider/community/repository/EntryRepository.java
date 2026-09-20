package com.kite.libai.provider.community.repository;

import com.kite.libai.core.repository.BaseRepository;
import com.kite.libai.common.result.PageResult;
import com.kite.libai.provider.community.model.entity.Entry;
import com.kite.libai.provider.community.model.request.EntryQueryParam;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;
import java.util.List;

@Repository
public interface EntryRepository extends BaseRepository<Entry> {

    boolean updateStatus(Long id, String status);

    List<Entry> batchGetByIdAndStatus(List<Long> entryIds, String status);

    List<Entry> getEntriesByAccountAndReleasedAt(Long accountId, LocalDateTime releasedAt);

    List<Entry> getEntriesByAccountAndReleasedAt(List<Long> accountIds, LocalDateTime releasedAt);

    PageResult<Entry> pageGetEntries(EntryQueryParam queryParam);
}
