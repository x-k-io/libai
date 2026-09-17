package com.kite.libai.provider.community.repository;

import com.kite.libai.boot.repository.BaseRepository;
import com.kite.libai.core.result.PageResult;
import com.kite.libai.provider.community.model.entity.Reply;

public interface ReplyRepository extends BaseRepository<Reply> {
    PageResult<Reply> pageGetByCommentId(Long commentId, int pageNum, int pageSize);
}
