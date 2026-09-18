package com.kite.libai.provider.message.model.request;

import java.io.Serializable;
import java.time.LocalDateTime;

import lombok.Data;

@Data
public class NoticeRequest implements Serializable {
        private String type;

    private Long receiveId;

    private String content;

    private LocalDateTime createdAt;

}
