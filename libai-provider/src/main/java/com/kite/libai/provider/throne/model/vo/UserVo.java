package com.kite.libai.provider.throne.model.vo;

import java.io.Serializable;
import java.time.LocalDateTime;

import lombok.Data;

@Data
public class UserVo implements Serializable {

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
