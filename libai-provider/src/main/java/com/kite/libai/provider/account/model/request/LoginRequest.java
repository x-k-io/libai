package com.kite.libai.provider.account.model.request;

import java.io.Serializable;

import jakarta.validation.constraints.NotNull;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;


@Data
@Schema
public class LoginRequest implements Serializable {

    @Schema(description = "登录名", example = "admin")
    private String loginName;

    @Schema(description = "密码", example = "e10adc3949ba59abbe56e057f20f883e")
    private String password;

    @Schema(description = "授权码", example = "code")
    private String code;

    @NotNull
    @Schema(description = "APP,WEB,MINI", example = "WEB")
    private String channel;

    @NotNull
    @Schema(description = "PWD_LOGIN,MESSAGE_LOGIN,OAUTH_LOGIN,SCAN_LOGIN,QUICK_LOGIN", example = "PWD_LOGIN")
    private String type;
}
