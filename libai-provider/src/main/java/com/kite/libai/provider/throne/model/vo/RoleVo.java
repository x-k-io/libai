package com.kite.libai.provider.throne.model.vo;

import java.io.Serializable;
import java.time.LocalDateTime;

import lombok.Data;

@Data
public class RoleVo implements Serializable {

    private Long id;

    private String code;

    private String name;

    private String description;

    private LocalDateTime createTime;

    private LocalDateTime updateTime;

}
