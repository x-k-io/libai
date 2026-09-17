package com.kite.libai.provider.account.model.request;

import java.io.Serializable;

import javax.validation.constraints.NotBlank;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

@Data
@ApiModel
public class AppOauthRequest implements Serializable {

    @NotBlank
    @ApiModelProperty(value = "appCode", example = "appCode")
    private String appCode;

    @NotBlank
    @ApiModelProperty(value = "code", example = "code")
    private String code;
}
