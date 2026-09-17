package com.kite.libai.provider.community.model.request;

import java.io.Serializable;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

@Data
@ApiModel(value = "ChannelRequest", description = "频道")
public class ChannelRequest implements Serializable {
    private static final long serialVersionUID = 1L;

    @ApiModelProperty(value = "分类")
    private String category;

    @ApiModelProperty(value = "名称")
    private String name;
}
