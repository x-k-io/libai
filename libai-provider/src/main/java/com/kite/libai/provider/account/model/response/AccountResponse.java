package com.kite.libai.provider.account.model.response;

import java.io.Serializable;
import java.time.LocalDateTime;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

@Data
public class AccountResponse implements Serializable {

    private Long id;

    private String type;

    private String mobile;

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

    private String introduction;

    private Integer following;

    private Integer followers;

    private Integer entries;

    private Integer replies;

    private Integer likes;

    private Integer browses;

    private Integer plays;

    private LocalDateTime registeredAt;

    private String status;
}
