package com.kite.libai.provider.account.model.request;

import java.io.Serializable;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

@Data
@Schema(name = "UserForm", description = "用户信息")
public class UpdateAccountRequest implements Serializable {
    private static final long serialVersionUID = 1L;

    @Schema(description = "昵称")
    private String nickname;

    @Schema(description = "头像")
    private String avatar;

    @Schema(description = "性别:UNKNOWN-未知,FEMALE-女,MALE-男")
    private String gender;

    @Schema(description = "生日")
    private String birthday;

    @Schema(description = "简介")
    private String introduction;

    @Schema(description = "国家")
    private String country;

    @Schema(description = "省")
    private String province;

    @Schema(description = "市")
    private String city;

    @Schema(description = "区")
    private String district;

}
