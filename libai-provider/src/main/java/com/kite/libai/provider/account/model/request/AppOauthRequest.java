package com.kite.libai.provider.account.model.request;

import java.io.Serializable;

import jakarta.validation.constraints.NotBlank;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

@Data
@Schema
public class AppOauthRequest implements Serializable {

    @NotBlank
    @Schema(description = "appCode", example = "appCode")
    private String appCode;

    @NotBlank
    @Schema(description = "code", example = "code")
    private String code;
}
