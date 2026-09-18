package com.kite.libai.provider.message.model.response;

import java.io.Serializable;
import java.time.LocalDateTime;

import lombok.Data;

@Data
public class NoticeRemindResponse implements Serializable {
        private Long id;

    private Long accountId;

    private Integer system;

    private Integer likes;

    private Integer forwards;

    private Integer replies;

    private Integer follows;

    private LocalDateTime createdAt;

}
