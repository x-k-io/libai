package com.kite.libai.provider.community.model.response;

import java.util.List;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

@Data
@ApiModel(value = "TopicCategoryResponse", description = "话题分类")
public class TopicCategoryResponse {

    @ApiModelProperty(value = "分类")
    private String category;

    @ApiModelProperty(value = "名称")
    private String name;

    @ApiModelProperty(value = "话题")
    private List<TopicResponse> topics;
}
