package com.kite.libai.provider.community.model.common;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode(callSuper = true)
public class ArticleSection extends BaseSection {

    @ApiModelProperty(value = "主键")
    private Long id;

    @ApiModelProperty(value = "作者id")
    private Long authorId;

    @ApiModelProperty(value = "内容类型")
    private String type;

    @ApiModelProperty(value = "作者类型")
    private String authorType;

    @ApiModelProperty(value = "标题")
    private String title;

    @ApiModelProperty(value = "副标题")
    private String subtitle;

    @ApiModelProperty(value = "封面图")
    private String cover;

    @ApiModelProperty(value = "图片")
    private String pictures;

    @ApiModelProperty(value = "节选")
    private String excerpt;

    @ApiModelProperty(value = "内容")
    private String detail;

    @ApiModelProperty(value = "视频连接")
    private String video;

    @ApiModelProperty(value = "视频时长")
    private Integer videoLength;
}
