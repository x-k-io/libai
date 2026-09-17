package com.kite.libai.provider.message.model.entity;

import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

import java.io.Serializable;
import java.time.LocalDateTime;

@Data
@TableName("t_im_message")
public class Message implements Serializable {

    private Long id;

    private String type;

    private Long chatId;

    private Long accountId;

    private String content;

    private LocalDateTime createdAt;
}
