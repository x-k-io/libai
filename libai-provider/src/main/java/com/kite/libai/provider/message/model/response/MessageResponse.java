package com.kite.libai.provider.message.model.response;

import java.io.Serializable;
import java.time.LocalDateTime;

import lombok.Data;

@Data
public class MessageResponse implements Serializable {
        private Long id;

    private String type;

    private Long chatId;

    private Long accountId;

    private String content;

    private LocalDateTime createdAt;

    private String nickname;

    private String avatar;

}
