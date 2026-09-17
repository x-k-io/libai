package com.kite.libai.provider.community.model.entity;

import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

import java.time.LocalDateTime;

@Data
@TableName("t_biz_favorite")
public class Favorite {

    private Long id;

    private Long accountId;

    private String entityType;

    private Long entityId;

    private Long entryId;

    private LocalDateTime createdAt;
}
