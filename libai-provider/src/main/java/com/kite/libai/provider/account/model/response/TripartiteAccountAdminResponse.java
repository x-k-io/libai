package com.kite.libai.provider.account.model.response;

import java.io.Serializable;
import java.time.LocalDateTime;

import lombok.Data;

@Data
public class TripartiteAccountAdminResponse implements Serializable {
        private Long id;

    private String type;

    private String appCode;

    private String appId;

    private String openId;

    private String unionId;

    private String mobile;

    private String nickname;

    private String gender;

    private String avatar;

    private String country;

    private String province;

    private String city;

    private Long accountId;

    private LocalDateTime createdAt;

    private String createdBy;

    private LocalDateTime updatedAt;

    private String updatedBy;

    private Boolean isDeleted;

}
