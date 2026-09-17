package com.kite.libai.provider.message.repository;

import com.kite.libai.boot.repository.BaseRepository;
import com.kite.libai.core.result.PageResult;
import com.kite.libai.provider.message.model.entity.Notice;

import java.util.List;

public interface NoticeRepository extends BaseRepository<Notice> {
    PageResult<Notice> getNotices(Long accountId, List<String> types, int pageNum, int pageSize);
}
