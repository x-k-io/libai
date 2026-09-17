package com.kite.libai.provider.throne.model.entity;

import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

import java.io.Serializable;
import java.time.LocalDateTime;

@Data
@TableName("auth_role_permission")
public class RolePermission implements Serializable {
    
    private Long id;

    private Long roleId;

    private Long permissionId;

    private LocalDateTime createTime;
}
