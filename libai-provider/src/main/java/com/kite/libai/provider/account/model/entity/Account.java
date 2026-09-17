package com.kite.libai.provider.account.model.entity;

import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

import java.io.Serializable;
import java.time.LocalDateTime;

@Data
@TableName("auth_account")
public class Account implements Serializable {

    private Long id;

    private String type;

    private String mobile;

    private String password;

    private String name;

    private String nickname;

    private String initials;

    private String avatar;

    private String gender;

    private String birthday;

    private String country;

    private String province;

    private String city;

    private String district;

    private Boolean identified;

    private String introduction;

    private LocalDateTime registeredAt;

    private String status;

    private LocalDateTime createdAt;

    private LocalDateTime updatedAt;
}
