package com.kite.libai.provider.community.service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.function.Function;
import java.util.stream.Collectors;

import com.kite.libai.boot.repository.BaseService;
import com.kite.libai.core.result.PageResult;
import com.kite.libai.provider.community.model.entity.Reply;
import com.kite.libai.provider.community.repository.ReplyRepository;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

import org.apache.commons.collections4.CollectionUtils;
import com.kite.libai.provider.community.enums.DataType;

import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
@AllArgsConstructor
public class ReplyService extends BaseService<ReplyRepository, Reply> {

    /**
     * 批量查询回复
     *
     * @param ids ids
     * @return Map<Long, Reply>
     */
    public Map<Long, Reply> batchGet(List<Long> ids) {
        List<Reply> list = this.batchGetByIds(ids);
        if (CollectionUtils.isNotEmpty(list)) {
            return list.stream().collect(Collectors.toMap(Reply::getId, Function.identity()));
        }
        return new HashMap<>(16);
    }

    @Async
    public void add(Long replyId, DataType dataType) {
        Reply reply = this.getById(replyId);
        if (reply == null) {
            return;
        }
        if (dataType == DataType.LIKES) {
            reply.setLikes(reply.getLikes() + 1);
            this.updateById(reply);
        }
    }

    @Async
    public void reduce(Long replyId, DataType dataType) {
        Reply reply = this.getById(replyId);
        if (reply == null) {
            return;
        }
        if (dataType == DataType.LIKES) {
            reply.setLikes(reply.getLikes() - 1);
            this.updateById(reply);
        }
    }

    public PageResult<Reply> pageGetByCommentId(Long commentId, int pageNum, int pageSize) {
            return null;
    }
}
