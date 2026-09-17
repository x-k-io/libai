package com.kite.libai.provider.account.model.entity;

import lombok.Data;

import java.io.Serializable;
import java.time.LocalDateTime;

@Data
public class KiteAccountSession implements Serializable {
    private String refreshToken;
    private Long accountId;
    private String loginType;
    private String loginChannel;
    private String deviceId;
    private LocalDateTime loginTime;
}
