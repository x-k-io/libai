package com.kite.libai.provider.account.model.request;

import java.io.Serializable;

import jakarta.validation.constraints.NotBlank;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Schema(description = "WeChatBindForm")
@AllArgsConstructor
@NoArgsConstructor
public class WeChatBindRequest implements Serializable {

    private static final long serialVersionUID = 1933368636932422141L;

    @NotBlank
    @Schema(description = "appCode", required = true)
    private String appCode;

    @NotBlank(message = "绑定st不能为空")
    @Schema(description = "绑定微信st")
    private String bindWeChatSt;

    @Schema(description = "微信用户信息加密数据")
    private String encryptedData;

    @Schema(description = "加密算法的初始向量")
    private String iv;

    @Schema(description = "登录名")
    private String loginName;

    @Schema(description = "验证码")
    private String smsCode;
}
