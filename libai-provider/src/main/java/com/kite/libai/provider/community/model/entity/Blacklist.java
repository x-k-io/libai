package com.kite.libai.provider.community.model.entity;

import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

import java.io.Serializable;
import java.time.LocalDateTime;

@Data
@TableName("t_biz_blacklist")
public class Blacklist implements Serializable {

    private Long id;

    private Long accountId;

    private Long theirId;

    private LocalDateTime createdAt;

}
