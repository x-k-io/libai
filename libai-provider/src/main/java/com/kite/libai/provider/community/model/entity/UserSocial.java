package com.kite.libai.provider.community.model.entity;

import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

import java.io.Serializable;
import java.time.LocalDateTime;

@Data
@TableName("t_biz_user_social")
public class UserSocial implements Serializable {

    private Long id;

    private Long accountId;

    private Integer following;

    private Integer followers;

    private Integer entries;

    private Integer replies;

    private Integer likes;

    private Integer browses;

    private Integer plays;

    private LocalDateTime lastReleasedAt;

    private LocalDateTime createdAt;

    private LocalDateTime updatedAt;
}
