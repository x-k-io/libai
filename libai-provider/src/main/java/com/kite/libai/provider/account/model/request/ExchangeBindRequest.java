package com.kite.libai.provider.account.model.request;

import java.io.Serializable;

import javax.validation.constraints.NotBlank;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

@Data
@ApiModel(description = "ExchangeBindForm")
public class ExchangeBindRequest implements Serializable {
    private static final long serialVersionUID = 1933368636932422141L;

    @NotBlank
    @ApiModelProperty(value = "appCode", required = true)
    private String appCode;

    @NotBlank
    @ApiModelProperty(value = "换绑st", example = "123456")
    private String exchangeSt;

    @NotBlank
    @ApiModelProperty(value = "是否确认换绑 true换绑 false取消换绑", example = "true")
    private Boolean sure;
}
