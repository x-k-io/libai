package com.kite.libai.provider.message.model.entity;

import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

import java.io.Serializable;
import java.time.LocalDateTime;

@Data
@TableName("t_im_notice_remind")
public class NoticeRemind implements Serializable {

    private Long id;

    private Long accountId;

    private Integer system;

    private Integer likes;

    private Integer forwards;

    private Integer replies;

    private Integer follows;

    private LocalDateTime createdAt;
}
