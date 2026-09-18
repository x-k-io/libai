package com.kite.libai.provider.account.model.response;

import java.io.Serializable;
import java.time.LocalDateTime;

import lombok.Data;

@Data
public class UserAdminResponse implements Serializable {
        private Long id;

    private String loginName;

    private String password;

    private String name;

    private String mobile;

    private String avatar;

    private String gender;

    private String email;

    private String birthday;

    private String status;

    private String createdBy;

    private LocalDateTime createdAt;

    private String updatedBy;

    private LocalDateTime updatedAt;

    private Boolean isDeleted;

}
