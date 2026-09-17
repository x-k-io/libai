package com.kite.libai.provider.community.presenter;

import com.kite.libai.provider.community.event.producer.FavoriteEventProducer;
import com.kite.libai.provider.community.model.entity.Entry;
import com.kite.libai.provider.community.model.entity.Favorite;
import com.kite.libai.provider.community.model.request.FavoriteRequest;
import org.springframework.stereotype.Component;

import com.kite.libai.core.context.RequestContextUtils;
import com.kite.libai.core.exception.ServiceException;
import com.kite.libai.core.result.SystemCode;
import com.kite.libai.provider.community.service.EntryService;
import com.kite.libai.provider.community.service.FavoriteService;

import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Component
@AllArgsConstructor
public class FavoritePresenter {

    private final EntryService entryService;

    private final FavoriteService favoriteService;

    private final FavoriteEventProducer favoriteEventProducer;

    /**
     * 创建收藏
     *
     * @param request request
     * @return boolean
     */
    public boolean favorites(FavoriteRequest request) {
        Long accountId = RequestContextUtils.getAccountId();
        Boolean favorites = favoriteService.isFavorites(accountId, request.getEntryId());
        if (favorites) {
            return Boolean.TRUE;
        }
        // 查询作品信息
        Entry entry = entryService.getById(request.getEntryId());
        if (entry == null) {
            throw new ServiceException("未查询到作品信息！");
        }
        Favorite favorite = new Favorite();
        favorite.setAccountId(accountId);
        favorite.setEntityType(entry.getEntityType());
        favorite.setEntityId(entry.getEntityId());
        favorite.setEntryId(entry.getId());
        boolean temp = favoriteService.save(favorite);
        // 发送收藏事件
        favoriteEventProducer.sendFavoriteEvent(favorite);
        return temp;
    }

    public boolean cancel(Long entryId) {
        Favorite favorite = favoriteService.getByAccountIdAndEntryId(RequestContextUtils.getAccountId(), entryId);
        if (favorite == null) {
            return Boolean.TRUE;
        }
        boolean temp = favoriteService.deleteById(favorite.getId());
        // 发送取消收藏事件
        favoriteEventProducer.sendCancelFavoriteEvent(favorite);
        return temp;
    }
}
