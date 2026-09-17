package com.kite.libai.provider.message.presenter;

import com.kite.libai.core.utils.BeanUtils;
import com.kite.libai.provider.message.model.response.NoticeRemindResponse;
import com.kite.libai.provider.message.model.entity.NoticeRemind;
import com.kite.libai.provider.message.service.NoticeRemindService;
import org.springframework.stereotype.Component;

import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Component
@AllArgsConstructor
public class NoticeRemindPresenter {

    private final NoticeRemindService noticeRemindService;

    public NoticeRemindResponse getByAccountId(Long accountId) {
        NoticeRemind noticeRemind = noticeRemindService.getByAccountId(accountId);
        return BeanUtils.copy(noticeRemind, NoticeRemindResponse.class);
    }

}
