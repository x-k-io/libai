package com.kite.libai.provider.account.model.request;

import java.io.Serializable;

import javax.validation.constraints.NotNull;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;


@Data
@ApiModel
public class LoginRequest implements Serializable {

    @ApiModelProperty(value = "登录名", example = "admin")
    private String loginName;

    @ApiModelProperty(value = "密码", example = "e10adc3949ba59abbe56e057f20f883e")
    private String password;

    @ApiModelProperty(value = "授权码", example = "code")
    private String code;

    @NotNull
    @ApiModelProperty(value = "APP,WEB,MINI", example = "WEB")
    private String channel;

    @NotNull
    @ApiModelProperty(value = "PWD_LOGIN,MESSAGE_LOGIN,OAUTH_LOGIN,SCAN_LOGIN,QUICK_LOGIN", example = "PWD_LOGIN")
    private String type;
}
