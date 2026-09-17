package com.kite.libai.provider.community.service;

import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

import com.baomidou.mybatisplus.core.toolkit.ObjectUtils;
import com.kite.libai.boot.repository.BaseService;
import com.kite.libai.provider.community.model.entity.Like;
import com.kite.libai.provider.community.repository.LikeRepository;
import org.springframework.stereotype.Service;

import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
@AllArgsConstructor
public class LikeService extends BaseService<LikeRepository, Like> {

    public Like getLike(Long accountId, Long entityId) {
        return this.repository.getLike(accountId, entityId);
    }

    /**
     * 查询是否已点赞
     *
     * @param accountId accountId
     * @param entityId  entityId
     * @return Boolean
     */
    public Boolean isLiked(Long accountId, Long entityId) {
        Like like = this.repository.getLike(accountId, entityId);
        return ObjectUtils.isNotNull(like);
    }

    /**
     * 批量查询是否已点赞
     *
     * @param accountId accountId
     * @param ids       ids
     * @return Set<Long>
     */
    public Set<Long> liked(Long accountId, List<Long> ids) {
        List<Like> likes = this.repository.getLikeList(accountId, ids);
        return likes.stream().map(Like::getEntityId).collect(Collectors.toSet());
    }
}
