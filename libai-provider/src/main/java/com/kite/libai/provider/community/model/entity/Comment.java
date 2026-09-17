package com.kite.libai.provider.community.model.entity;

import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

import java.io.Serializable;
import java.time.LocalDateTime;

@Data
@TableName("t_biz_comment")
public class Comment implements Serializable {

    private Long id;

    private Long accountId;

    private String entityType;

    private Long entityId;

    private Long entryId;

    private String content;

    private Integer likes;

    private Integer replies;

    private Boolean authored;

    private LocalDateTime createdAt;
}
