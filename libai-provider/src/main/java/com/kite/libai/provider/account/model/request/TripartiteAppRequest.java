package com.kite.libai.provider.account.model.request;

import java.io.Serializable;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

@Data
@Schema(name = "TripartiteAppForm", description = "应用")
public class TripartiteAppRequest implements Serializable {
    private static final long serialVersionUID = 1L;

    @Schema(description = "WX")
    private String type;

    @Schema(description = "名称")
    private String name;

    @Schema(description = "appCode")
    private String appCode;

    @Schema(description = "appId")
    private String appId;

    @Schema(description = "appSecret")
    private String appSecret;

    @Schema(description = "mchId")
    private String mchId;

    @Schema(description = "appKey")
    private String appKey;

    @Schema(description = "是否开放平台")
    private Boolean isPlatform;

}
