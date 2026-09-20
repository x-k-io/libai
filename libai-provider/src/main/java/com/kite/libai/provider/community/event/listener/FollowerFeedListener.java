package com.kite.libai.provider.community.event.listener;

import com.kite.libai.provider.community.event.model.EntryEvent;
import com.kite.libai.provider.community.presenter.FollowerFeedPresenter;
import org.springframework.stereotype.Component;

import com.google.common.eventbus.Subscribe;
import com.kite.libai.core.event.core.BaseListener;
import com.kite.libai.provider.community.enums.EntryEventType;

import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Component
@AllArgsConstructor
public class FollowerFeedListener implements BaseListener {

    private final FollowerFeedPresenter followerFeedPresenter;

    @Subscribe
    public void process(EntryEvent event) {
        if (event == null) {
            return;
        }
        // 作品发布成功事件
        if (EntryEventType.RELEASED.getType().equals(event.getType())) {
            // 作品发布成功加入到好友关注feed
            followerFeedPresenter.addFollowers(event.getAuthorId(), event.getEntryId(), System.currentTimeMillis());
        }
    }
}
