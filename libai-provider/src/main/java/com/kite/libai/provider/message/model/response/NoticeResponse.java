package com.kite.libai.provider.message.model.response;

import java.io.Serializable;
import java.time.LocalDateTime;

import com.kite.libai.provider.message.model.response.notice.BaseNoticeResponse;

import lombok.Data;

@Data
public class NoticeResponse implements Serializable {
        private Long id;

    private String type;

    private Long receiveId;

    private BaseNoticeResponse detail;

    private LocalDateTime createdAt;

}
