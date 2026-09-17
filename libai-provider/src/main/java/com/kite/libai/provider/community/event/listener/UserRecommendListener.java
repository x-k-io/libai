package com.kite.libai.provider.community.event.listener;

import java.time.LocalDateTime;

import com.kite.libai.provider.community.event.model.EntryEvent;
import com.kite.libai.provider.community.presenter.UserRecommendPresenter;
import org.springframework.stereotype.Component;

import com.google.common.eventbus.Subscribe;
import com.kite.libai.boot.event.core.BaseListener;
import com.kite.libai.provider.community.enums.EntryEventType;

import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Component
@AllArgsConstructor
public class UserRecommendListener implements BaseListener {

    private final UserRecommendPresenter userRecommendPresenter;

    @Subscribe
    public void process(EntryEvent event) {
        if (event == null) {
            return;
        }
        // 作品发布成功事件
        if (EntryEventType.RELEASED.getType().equals(event.getType())) {
            // 将作者添加到用户推荐列表
            userRecommendPresenter.addRecommend(event.getAuthorId(), LocalDateTime.now());
        }
    }
}
