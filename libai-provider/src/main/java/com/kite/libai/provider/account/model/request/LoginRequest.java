package com.kite.libai.provider.account.model.request;

import java.io.Serializable;

import jakarta.validation.constraints.NotNull;

import lombok.Data;


@Data
public class LoginRequest implements Serializable {

    private String loginName;

    private String password;

    private String code;

    @NotNull
    private String channel;

    @NotNull
    private String type;
}
