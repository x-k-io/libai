package com.kite.libai.provider.throne.model.cache;

import lombok.Data;

import java.io.Serializable;
import java.time.LocalDateTime;

@Data
public class KiteUserSession implements Serializable {
    private String refreshToken;
    private Long userId;
    private String deviceId;
    private LocalDateTime loginTime;
}
