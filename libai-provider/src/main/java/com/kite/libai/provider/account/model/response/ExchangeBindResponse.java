package com.kite.libai.provider.account.model.response;

import java.io.Serializable;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;


@AllArgsConstructor
@NoArgsConstructor
@Data
@ApiModel(description = "ExchangeBindVo")
public class ExchangeBindResponse implements Serializable {

    private static final long serialVersionUID = -6204617647073168548L;
    @ApiModelProperty("code")
    private String code;

    @ApiModelProperty("换绑st")
    private String exchangeSt;

    @ApiModelProperty("微信昵称")
    private String nickname;


}
