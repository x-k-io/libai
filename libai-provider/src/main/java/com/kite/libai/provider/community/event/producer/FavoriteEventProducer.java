package com.kite.libai.provider.community.event.producer;

import com.kite.libai.provider.community.model.entity.Favorite;
import org.springframework.stereotype.Service;

import com.kite.libai.core.event.core.KiteEventService;
import com.kite.libai.provider.community.enums.FavoriteEventType;
import com.kite.libai.provider.community.event.model.FavoriteEvent;

import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
@AllArgsConstructor
public class FavoriteEventProducer {

    private final KiteEventService kiteEventService;

    /**
     * 发送收藏事件
     *
     * @param favorite favorite
     */
    public void sendFavoriteEvent(Favorite favorite) {
        FavoriteEvent event = new FavoriteEvent(FavoriteEventType.FAVORITES.getType(), favorite);
        kiteEventService.post(event);
    }

    /**
     * 发送取消收藏事件
     *
     * @param favorite favorite
     */
    public void sendCancelFavoriteEvent(Favorite favorite) {
        FavoriteEvent event = new FavoriteEvent(FavoriteEventType.CANCEL.getType(), favorite);
        kiteEventService.post(event);
    }
}
