package com.kite.libai.provider.account.model.response;

import java.io.Serializable;
import java.time.LocalDateTime;

import lombok.Data;

@Data
public class TripartiteAppResponse implements Serializable {
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

    private Boolean isDeleted;

}
