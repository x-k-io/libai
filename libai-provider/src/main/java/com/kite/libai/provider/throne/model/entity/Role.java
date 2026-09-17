package com.kite.libai.provider.throne.model.entity;

import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

import java.io.Serializable;
import java.time.LocalDateTime;

@Data
@TableName("auth_role")
public class Role implements Serializable {

    private Long id;

    private String code;

    private String name;

    private String description;

    private LocalDateTime createTime;

    private LocalDateTime updateTime;
}
