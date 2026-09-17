package com.kite.libai.provider.message.model.entity;

import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

import java.io.Serializable;
import java.time.LocalDateTime;

@Data
@TableName("t_im_chat_detail")
public class ChatDetail implements Serializable {

    private Long id;

    private Long chatId;

    private Long accountId;

    private Long friendId;

    private String relation;

    private Integer unread;

    private String status;

    private LocalDateTime createdAt;
}
