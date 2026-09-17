package com.kite.libai.provider.account.model.entity;

import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

import java.io.Serializable;
import java.time.LocalDateTime;

@Data
@TableName("auth_account_login_log")
public class AccountLoginLog implements Serializable {
    private Long id;
    private Long accountId;
    private String accountType;
    private String loginType;
    private String loginChannel;
    private String appName;
    private String appVersion;
    private String deviceId;
    private String ip;
    private String userAgent;
    private LocalDateTime loginTime;
    private LocalDateTime createTime;
}
