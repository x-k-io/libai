package com.kite.libai.provider.community.model.request;

import java.io.Serializable;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

@Data
@ApiModel(value = "ArticleRequest", description = "文章")
public class ArticleRequest implements Serializable {
    private static final long serialVersionUID = 1L;

    @ApiModelProperty(value = "作者id")
    private Long accountId;

    @ApiModelProperty(value = "文章类型")
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
    private String picture;

    @ApiModelProperty(value = "节选")
    private String excerpt;

    @ApiModelProperty(value = "文章详情")
    private String detail;

    @ApiModelProperty(value = "视频连接")
    private String video;

    @ApiModelProperty(value = "频道id")
    private Long channelId;

    @ApiModelProperty(value = "圈子id")
    private Long circleId;

    @ApiModelProperty(value = "地点id")
    private Long placeId;

    @ApiModelProperty(value = "经度")
    private String longitude;

    @ApiModelProperty(value = "纬度")
    private String latitude;

    @ApiModelProperty(value = "国家：中国")
    private String country;

    @ApiModelProperty(value = "省：北京市")
    private String province;

    @ApiModelProperty(value = "市：北京市")
    private String city;

    @ApiModelProperty(value = "区：朝阳区")
    private String district;

    @ApiModelProperty(value = "位置：望京国际中心·北京")
    private String place;
}
