package com.kite.libai.provider.community.model.request;

import java.io.Serializable;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

@Data
@Schema(name = "ArticleRequest", description = "文章")
public class ArticleRequest implements Serializable {
    private static final long serialVersionUID = 1L;

    @Schema(description = "作者id")
    private Long accountId;

    @Schema(description = "文章类型")
    private String type;

    @Schema(description = "作者类型")
    private String authorType;

    @Schema(description = "标题")
    private String title;

    @Schema(description = "副标题")
    private String subtitle;

    @Schema(description = "封面图")
    private String cover;

    @Schema(description = "图片")
    private String picture;

    @Schema(description = "节选")
    private String excerpt;

    @Schema(description = "文章详情")
    private String detail;

    @Schema(description = "视频连接")
    private String video;

    @Schema(description = "频道id")
    private Long channelId;

    @Schema(description = "圈子id")
    private Long circleId;

    @Schema(description = "地点id")
    private Long placeId;

    @Schema(description = "经度")
    private String longitude;

    @Schema(description = "纬度")
    private String latitude;

    @Schema(description = "国家：中国")
    private String country;

    @Schema(description = "省：北京市")
    private String province;

    @Schema(description = "市：北京市")
    private String city;

    @Schema(description = "区：朝阳区")
    private String district;

    @Schema(description = "位置：望京国际中心·北京")
    private String place;
}
