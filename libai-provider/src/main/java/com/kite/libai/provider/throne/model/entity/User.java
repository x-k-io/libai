package com.kite.libai.provider.throne.model.entity;

import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

import java.io.Serializable;
import java.time.LocalDateTime;

@Data
@TableName("auth_user")
public class User implements Serializable {

    private Long id;

    private String username;

    private String password;

    private String name;

    private String mobile;

    private String avatar;

    private String email;

    private String status;

    private LocalDateTime createTime;

    private LocalDateTime updateTime;
}
