package com.kite.libai.provider.account.model.entity;

import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

import java.io.Serializable;
import java.time.LocalDateTime;

@Data
@TableName("t_sys_tripartite_app")
public class TripartiteApp implements Serializable {
        private Long id;

    private String appCode;

    private String type;

    private String name;

    private String appId;

    private String appSecret;

    private String mchId;

    private String mchSecret;

    private Boolean isPlatform;

    private LocalDateTime createdAt;

    private String createdBy;

    private LocalDateTime updatedAt;

    private String updatedBy;
}
