package com.kite.libai.provider.throne.model.request;

import java.io.Serializable;

import jakarta.validation.constraints.NotNull;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

@Data
@Schema
public class AdminLoginRequest implements Serializable {

    @Schema(description = "登录名", example = "admin")
    private String loginName;

    @Schema(description = "密码", example = "e10adc3949ba59abbe56e057f20f883e")
    private String password;
}
