package com.kite.libai.provider.throne.model.entity;

import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

import java.io.Serializable;
import java.time.LocalDateTime;

@Data
@TableName("auth_user_role")
public class UserRole implements Serializable {

    private Long id;

    private Long userId;

    private Long roleId;

    private LocalDateTime createTime;
}
