package com.kite.libai.provider.community.model.entity;

import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

import java.io.Serializable;
import java.time.LocalDateTime;

@Data
@TableName("t_biz_article")
public class Article implements Serializable {

    private Long id;

    private Long authorId;

    private String type;

    private String authorType;

    private String title;

    private String subtitle;

    private String cover;

    private String pictures;

    private String excerpt;

    private String detail;

    private String video;

    private Integer videoLength;

    private LocalDateTime createdAt;

    private LocalDateTime updatedAt;
}
