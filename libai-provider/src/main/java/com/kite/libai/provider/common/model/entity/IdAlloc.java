package com.kite.libai.provider.common.model.entity;

import lombok.Data;

import java.io.Serializable;
import java.time.LocalDateTime;

@Data
public class IdAlloc implements Serializable {
    private Long id;
    private String bizType;
    private Long maxId;
    private Integer step;
    private Integer incrementMax;
    private Long version;
    private LocalDateTime updateTime;
}
