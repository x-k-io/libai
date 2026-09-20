package com.kite.libai.provider.message.handle;

import com.kite.libai.common.utils.BeanUtils;
import com.kite.libai.provider.message.model.dto.SystemNotice;
import com.kite.libai.provider.message.model.entity.Notice;
import com.kite.libai.provider.message.model.response.NoticeResponse;
import org.springframework.stereotype.Component;

import com.kite.libai.common.utils.JsonUtils;
import com.kite.libai.provider.message.enums.NoticeType;
import com.kite.libai.provider.message.model.response.notice.SystemNoticeResponse;

import lombok.AllArgsConstructor;

@Component
@AllArgsConstructor
public class SystemNoticeHandle implements NoticeHandle {

    @Override
    public String getType() {
        return NoticeType.SYSTEM.getType();
    }

    @Override
    public NoticeResponse handle(Notice notice) {
        NoticeResponse noticeResponse = new NoticeResponse();
        noticeResponse.setId(notice.getId());
        noticeResponse.setType(notice.getType());
        noticeResponse.setReceiveId(notice.getReceiveId());
        SystemNotice systemNotice = JsonUtils.parse(notice.getContent(), SystemNotice.class);
        SystemNoticeResponse response = BeanUtils.copy(systemNotice, SystemNoticeResponse.class);
        noticeResponse.setDetail(response);
        noticeResponse.setCreatedAt(notice.getCreatedAt());
        return noticeResponse;
    }
}
