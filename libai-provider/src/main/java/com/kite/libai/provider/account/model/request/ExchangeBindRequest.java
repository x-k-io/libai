package com.kite.libai.provider.account.model.request;

import java.io.Serializable;

import jakarta.validation.constraints.NotBlank;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

@Data
@Schema(description = "ExchangeBindForm")
public class ExchangeBindRequest implements Serializable {
    private static final long serialVersionUID = 1933368636932422141L;

    @NotBlank
    @Schema(description = "appCode", required = true)
    private String appCode;

    @NotBlank
    @Schema(description = "换绑st", example = "123456")
    private String exchangeSt;

    @NotBlank
    @Schema(description = "是否确认换绑 true换绑 false取消换绑", example = "true")
    private Boolean sure;
}
