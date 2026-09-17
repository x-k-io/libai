package com.kite.libai.provider.community.repository.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.kite.libai.boot.repository.MybatisBaseRepository;
import com.kite.libai.core.result.PageResult;
import com.kite.libai.provider.community.mapper.CommentMapper;
import com.kite.libai.provider.community.model.entity.Comment;
import com.kite.libai.provider.community.repository.CommentRepository;
import org.springframework.stereotype.Repository;


@Repository
public class CommonRepositoryImpl extends MybatisBaseRepository<CommentMapper, Comment> implements CommentRepository {
    @Override
    public PageResult<Comment> pageGetCommentByEntryId(Long entryId, int pageNum, int pageSize) {
        LambdaQueryWrapper<Comment> wrapper = new LambdaQueryWrapper<>();
        wrapper.eq(Comment::getEntryId, entryId);
        wrapper.orderByDesc(Comment::getCreatedAt);
        return this.pageGet(wrapper, pageNum, pageSize);
    }
}
