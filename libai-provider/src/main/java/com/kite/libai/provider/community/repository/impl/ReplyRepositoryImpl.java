package com.kite.libai.provider.community.repository.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.kite.libai.boot.repository.MybatisBaseRepository;
import com.kite.libai.core.result.PageResult;
import com.kite.libai.provider.community.mapper.ReplyMapper;
import com.kite.libai.provider.community.model.entity.Reply;
import com.kite.libai.provider.community.repository.ReplyRepository;
import org.springframework.stereotype.Repository;

@Repository
public class ReplyRepositoryImpl extends MybatisBaseRepository<ReplyMapper, Reply> implements ReplyRepository {

    @Override
    public PageResult<Reply> pageGetByCommentId(Long commentId, int pageNum, int pageSize) {
        LambdaQueryWrapper<Reply> wrapper = new LambdaQueryWrapper<>();
        wrapper.eq(Reply::getCommentId, commentId);
        wrapper.orderByDesc(Reply::getLikes, Reply::getCreatedAt);
        return this.pageGet(wrapper, pageNum, pageSize);
    }
}
