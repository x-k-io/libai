package com.kite.libai.provider.account.model.request;

import java.io.Serializable;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

@Data
@Schema(name = "AccountRequest", description = "用户信息")
public class CreateAccountRequest implements Serializable {
    private static final long serialVersionUID = 1L;

    @Schema(description = "用户类型：正常-normal,马甲号-vest")
    private String type;

    @Schema(description = "手机号")
    private String mobile;

    @Schema(description = "昵称")
    private String nickname;

    @Schema(description = "头像")
    private String avatar;
}
