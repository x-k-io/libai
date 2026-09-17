package com.kite.libai.provider.community.repository;

import com.kite.libai.boot.repository.BaseRepository;
import com.kite.libai.core.result.PageResult;
import com.kite.libai.provider.community.model.entity.Comment;
import org.springframework.stereotype.Repository;

@Repository
public interface CommentRepository extends BaseRepository<Comment> {

    PageResult<Comment> pageGetCommentByEntryId(Long entryId, int pageNum, int pageSize);
}
