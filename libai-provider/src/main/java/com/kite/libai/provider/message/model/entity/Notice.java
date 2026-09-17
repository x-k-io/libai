package com.kite.libai.provider.message.model.entity;

import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

import java.io.Serializable;
import java.time.LocalDateTime;

@Data
@TableName("t_im_notice")
public class Notice implements Serializable {

    private Long id;

    private String type;

    private Long receiveId;

    private String content;

    private LocalDateTime createdAt;

}
