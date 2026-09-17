package com.kite.libai.provider.community.model.response;

import java.util.List;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

@Data
@ApiModel(value = "ChannelCategoryResponse", description = "频道分类")
public class ChannelCategoryResponse {

    @ApiModelProperty(value = "分类")
    private String category;

    @ApiModelProperty(value = "名称")
    private String name;

    @ApiModelProperty(value = "频道")
    private List<ChannelResponse> channels;
}
