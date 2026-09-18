package com.kite.libai.provider.community.event.listener;

import com.kite.libai.provider.community.event.model.FollowerEvent;
import com.kite.libai.provider.community.presenter.FollowerFeedPresenter;
import org.springframework.stereotype.Component;

import com.google.common.eventbus.Subscribe;
import com.kite.libai.core.utils.JsonUtils;
import com.kite.libai.boot.event.core.BaseListener;
import com.kite.libai.provider.community.enums.FollowerEventType;
import com.kite.libai.provider.message.enums.NoticeType;
import com.kite.libai.provider.message.model.dto.FollowerNotice;
import com.kite.libai.provider.message.model.request.NoticeRequest;
import com.kite.libai.provider.message.presenter.NoticePresenter;

import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Component
@AllArgsConstructor
public class FollowerListener implements BaseListener {

    private final NoticePresenter noticePresenter;

    private final FollowerFeedPresenter followerFeedPresenter;

    @Subscribe
    public void process(FollowerEvent followerEvent) {
        if (followerEvent == null) {
            return;
        }
        if (FollowerEventType.FOLLOWER.getType().equals(followerEvent.getType())) {
            followerFeedPresenter.addFollowers(followerEvent.getAccountId(), followerEvent.getFriendId());
            // 发送通知
            FollowerNotice followerNotice = new FollowerNotice();
            followerNotice.setAccountId(followerEvent.getAccountId());
            NoticeRequest noticeRequest = new NoticeRequest();
            noticeRequest.setReceiveId(followerEvent.getFriendId());
            noticeRequest.setType(NoticeType.FOLLOW.getType());
            noticeRequest.setContent(JsonUtils.toJson(followerNotice));
            noticePresenter.create(noticeRequest);
        }
    }
}
