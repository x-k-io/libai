package com.kite.libai.provider.account.model.response;

import lombok.Data;

import java.io.Serializable;

@Data
public class AccountTokenVo implements Serializable {
    private String accessToken;
    private String refreshToken;
    private Long accessExpire;
}
