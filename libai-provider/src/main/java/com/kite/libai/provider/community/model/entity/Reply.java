package com.kite.libai.provider.community.model.entity;

import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

import java.io.Serializable;
import java.time.LocalDateTime;

@Data
@TableName("t_biz_reply")
public class Reply implements Serializable {

    private Long id;

    private Long accountId;

    private String entityType;

    private Long entityId;

    private Long entryId;

    private Long commentId;

    private Long toAccountId;

    private String content;

    private Integer likes;

    private Boolean authored;

    private LocalDateTime createdAt;
}
