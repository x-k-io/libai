package com.kite.libai.provider.account.model.entity;

import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

import java.io.Serializable;
import java.time.LocalDateTime;

@Data
@TableName("t_sys_tripartite_account")
public class TripartiteAccount implements Serializable {

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

}
