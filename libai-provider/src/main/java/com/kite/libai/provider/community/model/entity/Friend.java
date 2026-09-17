package com.kite.libai.provider.community.model.entity;

import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

import java.io.Serializable;
import java.time.LocalDateTime;

@Data
@TableName("t_biz_friend")
public class Friend implements Serializable {

    private Long id;

    private Long accountId;

    private Long friendId;

    private Boolean friend;

    private LocalDateTime createdAt;
}
