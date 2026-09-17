package com.kite.libai.provider.account.model.request;

import java.io.Serializable;

import javax.validation.constraints.NotBlank;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@ApiModel(description = "WeChatBindForm")
@AllArgsConstructor
@NoArgsConstructor
public class WeChatBindRequest implements Serializable {

    private static final long serialVersionUID = 1933368636932422141L;

    @NotBlank
    @ApiModelProperty(value = "appCode", required = true)
    private String appCode;

    @NotBlank(message = "绑定st不能为空")
    @ApiModelProperty(value = "绑定微信st")
    private String bindWeChatSt;

    @ApiModelProperty(value = "微信用户信息加密数据")
    private String encryptedData;

    @ApiModelProperty(value = "加密算法的初始向量")
    private String iv;

    @ApiModelProperty(value = "登录名")
    private String loginName;

    @ApiModelProperty(value = "验证码")
    private String smsCode;
}
