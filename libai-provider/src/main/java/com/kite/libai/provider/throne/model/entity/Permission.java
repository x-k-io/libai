package com.kite.libai.provider.throne.model.entity;

import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

import java.io.Serializable;
import java.time.LocalDateTime;

@Data
@TableName("auth_permission")
public class Permission implements Serializable {
    private Long id;

    private Long parentId;

    private String name;

    private String permission;

    private Integer orders;

    private LocalDateTime createTime;

    private LocalDateTime updateTime;
}
