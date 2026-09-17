package com.kite.libai.provider.account.model.response;

import java.io.Serializable;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;




@ApiModel("AppOauthVo")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class AppOauthResponse implements Serializable {

    private static final long serialVersionUID = 355869130014368020L;

    @ApiModelProperty(value = "登录code")
    private String code;

    @ApiModelProperty(value = "sessionSt")
    private String sessionSt;

    @ApiModelProperty(value = "绑定st")
    private String bindSt;
}
