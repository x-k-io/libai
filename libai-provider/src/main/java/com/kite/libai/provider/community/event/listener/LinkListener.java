package com.kite.libai.provider.community.event.listener;

import com.kite.libai.provider.community.event.model.LikeEvent;
import com.kite.libai.provider.community.model.entity.Entry;
import com.kite.libai.provider.community.service.EntryService;
import org.springframework.stereotype.Component;

import com.google.common.eventbus.Subscribe;
import com.kite.libai.common.utils.JsonUtils;
import com.kite.libai.core.event.core.BaseListener;
import com.kite.libai.provider.community.enums.EntityType;
import com.kite.libai.provider.community.enums.LikeEventType;
import com.kite.libai.provider.message.enums.NoticeType;
import com.kite.libai.provider.message.model.dto.LikeNotice;
import com.kite.libai.provider.message.model.request.NoticeRequest;
import com.kite.libai.provider.message.presenter.NoticePresenter;

import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Component
@AllArgsConstructor
public class LinkListener implements BaseListener {

    private final EntryService entryService;

    private final NoticePresenter noticePresenter;

    @Subscribe
    public void process(LikeEvent likeEvent) {
        if (likeEvent == null) {
            return;
        }
        if (EntityType.ENTRY.getType().equals(likeEvent.getEntityType())) {
            Entry entry = entryService.getById(likeEvent.getEntityId());
            if (entry == null) {
                return;
            }
            if (LikeEventType.LIKED.getType().equals(likeEvent.getType())) {
                // 点赞人不是作者本人发送通知
                if (!entry.getAuthorId().equals(likeEvent.getAccountId())) {
                    LikeNotice likeNotice = new LikeNotice();
                    likeNotice.setAccountId(likeEvent.getAccountId());
                    likeNotice.setArticleId(likeEvent.getEntityId());
                    NoticeRequest noticeRequest = new NoticeRequest();
                    noticeRequest.setReceiveId(entry.getAuthorId());
                    noticeRequest.setType(NoticeType.LIKE.getType());
                    noticeRequest.setContent(JsonUtils.toJson(likeNotice));
                    noticePresenter.create(noticeRequest);
                }
            }
        }
    }

}
