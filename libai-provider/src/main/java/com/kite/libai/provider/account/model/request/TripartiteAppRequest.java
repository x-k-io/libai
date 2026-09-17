package com.kite.libai.provider.account.model.request;

import java.io.Serializable;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

@Data
@ApiModel(value = "TripartiteAppForm", description = "应用")
public class TripartiteAppRequest implements Serializable {
    private static final long serialVersionUID = 1L;

    @ApiModelProperty(value = "WX")
    private String type;

    @ApiModelProperty(value = "名称")
    private String name;

    @ApiModelProperty(value = "appCode")
    private String appCode;

    @ApiModelProperty(value = "appId")
    private String appId;

    @ApiModelProperty(value = "appSecret")
    private String appSecret;

    @ApiModelProperty(value = "mchId")
    private String mchId;

    @ApiModelProperty(value = "appKey")
    private String appKey;

    @ApiModelProperty(value = "是否开放平台")
    private Boolean isPlatform;

}
