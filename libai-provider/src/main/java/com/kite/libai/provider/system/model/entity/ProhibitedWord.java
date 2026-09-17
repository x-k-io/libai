package com.kite.libai.provider.system.model.entity;

import java.io.Serializable;
import java.time.LocalDateTime;

import com.baomidou.mybatisplus.annotation.TableName;

import lombok.Data;

@Data
@TableName("t_base_prohibited_word")
public class ProhibitedWord implements Serializable {
    private static final long serialVersionUID = 1L;

    private Long id;

    private String type;

    private String word;

    private Boolean status;

    private LocalDateTime createdAt;

    private String createdBy;

    private LocalDateTime updatedAt;

    private String updatedBy;
}
