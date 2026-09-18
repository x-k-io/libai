package com.kite.libai.provider.account.model.response;

import java.io.Serializable;
import java.time.LocalDateTime;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

@Data
@Schema(name = "TripartiteAppAdminResponse", description = "应用")
public class TripartiteAppAdminResponse implements Serializable {
    private static final long serialVersionUID = 1L;

    @Schema(description = "主键")
    private Long id;

    @Schema(description = "appCode")
    private String appCode;

    @Schema(description = "WX")
    private String type;

    @Schema(description = "名称")
    private String name;

    @Schema(description = "appId")
    private String appId;

    @Schema(description = "appSecret")
    private String appSecret;

    @Schema(description = "商户appId")
    private String mchId;

    @Schema(description = "商户appSecret")
    private String mchSecret;

    @Schema(description = "是否开放平台")
    private Boolean isPlatform;

    @Schema(description = "创建时间")
    private LocalDateTime createdAt;

    @Schema(description = "创建者")
    private String createdBy;

    @Schema(description = "更新时间")
    private LocalDateTime updatedAt;

    @Schema(description = "更新者")
    private String updatedBy;

    @Schema(description = "是否删除0:否,1:是")
    private Boolean isDeleted;

}
