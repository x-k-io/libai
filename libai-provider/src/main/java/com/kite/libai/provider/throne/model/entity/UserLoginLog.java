package com.kite.libai.provider.throne.model.entity;

import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

import java.io.Serializable;
import java.time.LocalDateTime;

@Data
@TableName("auth_user_login_log")
public class UserLoginLog implements Serializable {
    private Long id;
    private Long userId;
    private String deviceId;
    private String ip;
    private String userAgent;
    private LocalDateTime loginTime;
    private LocalDateTime createTime;
}
