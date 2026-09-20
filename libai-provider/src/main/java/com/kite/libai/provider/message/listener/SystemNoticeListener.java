package com.kite.libai.provider.message.listener;

import com.kite.libai.provider.message.model.dto.SystemNotice;
import com.kite.libai.provider.message.model.request.NoticeRequest;
import org.springframework.context.event.EventListener;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Component;

import com.kite.libai.common.utils.JsonUtils;
import com.kite.libai.core.event.core.BaseListener;
import com.kite.libai.provider.message.enums.NoticeType;
import com.kite.libai.provider.message.event.SystemNoticeEvent;
import com.kite.libai.provider.message.presenter.NoticePresenter;

import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Component
@AllArgsConstructor
public class SystemNoticeListener implements BaseListener {

    private final NoticePresenter noticePresenter;

    @Async
    @EventListener(SystemNoticeEvent.class)
    public void process(SystemNoticeEvent systemNoticeEvent) {
        if (systemNoticeEvent == null) {
            return;
        }
        SystemNotice systemNotice = systemNoticeEvent.getSystemNotice();
        NoticeRequest noticeRequest = new NoticeRequest();
        noticeRequest.setReceiveId(systemNoticeEvent.getReceiveId());
        noticeRequest.setType(NoticeType.SYSTEM.getType());
        noticeRequest.setContent(JsonUtils.toJson(systemNotice));
        noticePresenter.create(noticeRequest);
    }
}
