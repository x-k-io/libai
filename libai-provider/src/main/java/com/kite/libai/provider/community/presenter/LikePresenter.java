package com.kite.libai.provider.community.presenter;

import com.kite.libai.provider.community.event.producer.LikeEventProducer;
import com.kite.libai.provider.community.model.entity.Like;
import com.kite.libai.provider.community.model.request.LikeRequest;
import org.springframework.stereotype.Component;

import com.kite.libai.core.context.RequestContextUtils;
import com.kite.libai.provider.community.service.LikeService;

import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Component
@AllArgsConstructor
public class LikePresenter {

    private final LikeService likeService;

    private final LikeEventProducer likeEventProducer;

    /**
     * 创建点赞
     *
     * @param request request
     * @return boolean
     */
    public boolean like(LikeRequest request) {
        Long accountId = RequestContextUtils.getAccountId();
        Boolean liked = likeService.isLiked(accountId, request.getEntityId());
        if (liked) {
            return Boolean.TRUE;
        }
        Like like = new Like();
        like.setAccountId(accountId);
        like.setEntityType(request.getEntityType());
        like.setEntityId(request.getEntityId());
        like.setEntryId(request.getEntryId());
        boolean temp = likeService.save(like);
        // 发送点赞事件
        likeEventProducer.sendLikeEvent(like);
        return temp;
    }


    public boolean cancel(Long entryId) {
        Like like = likeService.getLike(RequestContextUtils.getAccountId(), entryId);
        if (like == null) {
            return Boolean.TRUE;
        }
        boolean temp = likeService.deleteById(like.getId());
        // 发送取消点赞事件
        likeEventProducer.sendCancelLikeEvent(like);
        return temp;
    }
}
