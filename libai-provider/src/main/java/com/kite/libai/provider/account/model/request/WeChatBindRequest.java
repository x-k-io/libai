package com.kite.libai.provider.account.model.request;

import java.io.Serializable;

import jakarta.validation.constraints.NotBlank;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class WeChatBindRequest implements Serializable {

    private static final long serialVersionUID = 1933368636932422141L;

    @NotBlank
    private String appCode;

    @NotBlank(message = "绑定st不能为空")
    private String bindWeChatSt;

    private String encryptedData;

    private String iv;

    private String loginName;

    private String smsCode;
}
