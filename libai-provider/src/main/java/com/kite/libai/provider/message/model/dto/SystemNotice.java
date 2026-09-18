package com.kite.libai.provider.message.model.dto;

import java.io.Serializable;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode(callSuper = true)
public class SystemNotice extends BaseNotice implements Serializable {
    private static final long serialVersionUID = 1L;

    @Schema(description = "标题")
    private String title;

    @Schema(description = "副标题")
    private String subtitle;

    @Schema(description = "封面")
    private String cover;

    @Schema(description = "节选")
    private String excerpt;

    @Schema(description = "详情")
    private String detail;

    @Schema(description = "跳转连接")
    private String schema;
}
