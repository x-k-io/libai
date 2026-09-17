package com.kite.libai.provider.community.model.entity;

import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

import java.io.Serializable;
import java.time.LocalDateTime;

@Data
@TableName("t_biz_topic")
public class Topic implements Serializable {

    private Long id;

    private String category;

    private String icon;

    private String name;

    private String description;

    private Long orders;

    private Integer reads;

    private Integer mentions;

    private Integer authors;

    private LocalDateTime createdAt;

    private LocalDateTime updatedAt;
}
