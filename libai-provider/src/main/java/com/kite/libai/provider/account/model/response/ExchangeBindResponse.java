package com.kite.libai.provider.account.model.response;

import java.io.Serializable;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;


@AllArgsConstructor
@NoArgsConstructor
@Data
@Schema(description = "ExchangeBindVo")
public class ExchangeBindResponse implements Serializable {

    private static final long serialVersionUID = -6204617647073168548L;
    @Schema(description = "code")
    private String code;

    @Schema(description = "换绑st")
    private String exchangeSt;

    @Schema(description = "微信昵称")
    private String nickname;


}
