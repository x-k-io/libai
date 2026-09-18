package com.kite.libai.provider.throne.model.request;

import java.io.Serializable;

import jakarta.validation.constraints.NotNull;

import lombok.Data;

@Data
public class AdminLoginRequest implements Serializable {

    private String loginName;

    private String password;
}
