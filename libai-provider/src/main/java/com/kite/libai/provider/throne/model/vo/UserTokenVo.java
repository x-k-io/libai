package com.kite.libai.provider.throne.model.vo;

import lombok.Data;

import java.io.Serializable;

@Data
public class UserTokenVo implements Serializable {
    private String accessToken;
    private String refreshToken;
    private Long accessExpire;
}
