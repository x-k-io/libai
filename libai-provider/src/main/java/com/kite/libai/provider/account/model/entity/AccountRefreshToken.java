package com.kite.libai.provider.account.model.entity;

import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

import java.io.Serializable;
import java.time.LocalDateTime;

@Data
@TableName("auth_user_refresh_token")
public class AccountRefreshToken implements Serializable {
    private Long id;
    private Long accountId;
    private String refreshToken;
    private String deviceId;
    private String ip;
    private LocalDateTime expiresTime;
    private LocalDateTime loginTime;
    private LocalDateTime updateTime;
}
