package com.kite.libai.provider.account.model.response;

import java.io.Serializable;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;




@Schema(name = "AppOauthVo")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class AppOauthResponse implements Serializable {

    private static final long serialVersionUID = 355869130014368020L;

    @Schema(description = "登录code")
    private String code;

    @Schema(description = "sessionSt")
    private String sessionSt;

    @Schema(description = "绑定st")
    private String bindSt;
}
