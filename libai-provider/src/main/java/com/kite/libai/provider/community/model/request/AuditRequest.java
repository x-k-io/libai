package com.kite.libai.provider.community.model.request;

import java.io.Serializable;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

@Data
@ApiModel(value = "AuditRequest", description = "审核")
public class AuditRequest implements Serializable {
    private static final long serialVersionUID = 1L;

    @ApiModelProperty(value = "是否通过")
    private boolean pass;

    @ApiModelProperty(value = "拒绝原因")
    private String reason;
}
