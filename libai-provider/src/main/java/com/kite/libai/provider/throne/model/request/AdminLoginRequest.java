package com.kite.libai.provider.throne.model.request;

import java.io.Serializable;

import javax.validation.constraints.NotNull;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

@Data
@ApiModel
public class AdminLoginRequest implements Serializable {

    @ApiModelProperty(value = "登录名", example = "admin")
    private String loginName;

    @ApiModelProperty(value = "密码", example = "e10adc3949ba59abbe56e057f20f883e")
    private String password;
}
