package com.kite.libai.provider.throne.model.vo;

import java.time.LocalDateTime;

import lombok.Data;

@Data
public class PermissionVo {

    private Long id;

    private Long parentId;

    private String name;

    private String permission;

    private Integer orders;

    private LocalDateTime createTime;

    private LocalDateTime updateTime;
}
