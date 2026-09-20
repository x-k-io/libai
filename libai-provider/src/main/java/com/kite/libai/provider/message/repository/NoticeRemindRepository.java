package com.kite.libai.provider.message.repository;

import com.kite.libai.core.repository.BaseRepository;
import com.kite.libai.provider.message.model.entity.NoticeRemind;

public interface NoticeRemindRepository extends BaseRepository<NoticeRemind> {
    NoticeRemind getByAccountId(Long accountId);
}
