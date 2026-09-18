package com.kite.libai.provider.community.model.request;

import java.io.Serializable;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

@Data
@Schema(name = "AuditRequest", description = "审核")
public class AuditRequest implements Serializable {
    private static final long serialVersionUID = 1L;

    @Schema(description = "是否通过")
    private boolean pass;

    @Schema(description = "拒绝原因")
    private String reason;
}
