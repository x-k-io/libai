package com.kite.libai.provider.community.model.response;

import java.time.LocalDateTime;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

@Data
@ApiModel(value = "TopicResponse", description = "话题")
public class TopicResponse {

    @ApiModelProperty(value = "主键")
    private Long id;

    @ApiModelProperty(value = "分类")
    private String category;

    @ApiModelProperty(value = "图标")
    private String icon;

    @ApiModelProperty(value = "名称")
    private String name;

    @ApiModelProperty(value = "描述")
    private String description;

    @ApiModelProperty(value = "排序")
    private Long orders;

    @ApiModelProperty(value = "阅读数")
    private Integer reads;

    @ApiModelProperty(value = "讨论数")
    private Integer mentions;

    @ApiModelProperty(value = "作者人数")
    private Integer authors;

    @ApiModelProperty(value = "创建时间")
    private LocalDateTime createdAt;

    @ApiModelProperty(value = "更新时间")
    private LocalDateTime updatedAt;

}
