package com.kite.libai.provider.community.model.entity;

import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

import java.io.Serializable;
import java.time.LocalDateTime;

@Data
@TableName("t_biz_channel")
public class Channel implements Serializable {

    private Long id;

    private String category;

    private String name;

    private Long orders;

    private LocalDateTime createdAt;

    private LocalDateTime updatedAt;
}
