package com.kite.libai.provider.message.model.entity;

import com.baomidou.mybatisplus.annotation.FieldFill;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

import java.io.Serializable;
import java.time.LocalDateTime;

@Data
@TableName("t_im_chat")
public class Chat implements Serializable {

    private Long id;

    private Long lastMsgId;

    private String lastContent;

    private LocalDateTime lastedAt;

    @TableField(fill = FieldFill.INSERT)
    private LocalDateTime createdAt;
}
