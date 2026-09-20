package com.kite.libai.provider.community.service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.function.Function;
import java.util.stream.Collectors;

import com.kite.libai.core.repository.BaseService;
import com.kite.libai.common.result.PageResult;
import com.kite.libai.provider.community.model.entity.Comment;
import com.kite.libai.provider.community.repository.CommentRepository;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

import org.apache.commons.collections4.CollectionUtils;
import com.kite.libai.provider.community.enums.DataType;

import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
@AllArgsConstructor
public class CommentService extends BaseService<CommentRepository, Comment> {

    /**
     * 批量查询评论
     *
     * @param ids ids
     * @return Map<Long, Comment>
     */
    public Map<Long, Comment> batchGet(List<Long> ids) {
        List<Comment> list = batchGetByIds(ids);
        if (CollectionUtils.isNotEmpty(list)) {
            return list.stream().collect(Collectors.toMap(Comment::getId, Function.identity()));
        }
        return new HashMap<>(16);
    }

    @Async
    public void add(Long commentId, DataType dataType) {
        Comment comment = this.getById(commentId);
        if (comment == null) {
            log.info("未查询到评论 commentId:{}", commentId);
            return;
        }
        switch (dataType) {
            case LIKES:
                comment.setLikes(comment.getLikes() + 1);
            case REPLIES:
                comment.setLikes(comment.getReplies() + 1);
            default:
        }
        updateById(comment);
    }

    @Async
    public void reduce(Long commentId, DataType dataType) {
        Comment comment = this.getById(commentId);
        if (comment == null) {
            return;
        }
        switch (dataType) {
            case LIKES:
                comment.setLikes(comment.getLikes() - 1);
            case REPLIES:
                comment.setLikes(comment.getReplies() - 1);
            default:
        }
        updateById(comment);
    }

    public PageResult<Comment> pageGetCommentByEntryId(Long entryId, int pageNum, int pageSize) {
        return this.repository.pageGetCommentByEntryId(entryId, pageNum, pageSize);
    }
}
