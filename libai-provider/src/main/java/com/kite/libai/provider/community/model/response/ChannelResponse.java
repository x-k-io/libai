package com.kite.libai.provider.community.model.response;

import java.time.LocalDateTime;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

@Data
@ApiModel(value = "ChannelResponse", description = "频道")
public class ChannelResponse {

    @ApiModelProperty(value = "主键")
    private Long id;

    @ApiModelProperty(value = "分类")
    private String category;

    @ApiModelProperty(value = "名称")
    private String name;

    @ApiModelProperty(value = "排序")
    private Long orders;

    @ApiModelProperty(value = "创建时间")
    private LocalDateTime createdAt;

    @ApiModelProperty(value = "更新时间")
    private LocalDateTime updatedAt;

}
