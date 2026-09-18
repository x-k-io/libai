package com.kite.libai.provider.message.model.response;

import java.io.Serializable;
import java.time.LocalDateTime;

import lombok.Data;

@Data
public class ChatResponse implements Serializable {
        private Long id;

    private Long accountId;

    private String nickname;

    private String avatar;

    private String relation;

    private Integer unread;

    private Long lastMsgId;

    private String lastContent;

    private LocalDateTime lastedAt;

    private LocalDateTime createdAt;
}
