package com.kite.libai.provider.community.model.response;

import java.time.LocalDateTime;

import com.kite.libai.provider.community.model.common.BaseSection;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

@Data
@ApiModel(value = "EntryInternalResponse", description = "作品")
public class EntryInternalResponse {

    @ApiModelProperty(value = "主键")
    private Long id;

    @ApiModelProperty(value = "作者id")
    private Long authorId;

    @ApiModelProperty(value = "内容类型")
    private String type;

    @ApiModelProperty(value = "作者类型")
    private String authorType;

    @ApiModelProperty(value = "实体类型")
    private String entityType;

    @ApiModelProperty(value = "实体id")
    private Long entityId;

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

    @ApiModelProperty(value = "国家")
    private String country;

    @ApiModelProperty(value = "省")
    private String province;

    @ApiModelProperty(value = "市")
    private String city;

    @ApiModelProperty(value = "区县")
    private String district;

    @ApiModelProperty(value = "位置")
    private String place;

    @ApiModelProperty(value = "点赞数")
    private Integer likes;

    @ApiModelProperty(value = "回复数")
    private Integer replies;

    @ApiModelProperty(value = "浏览数")
    private Integer browses;

    @ApiModelProperty(value = "收藏数")
    private Integer favorites;

    @ApiModelProperty(value = "播放数")
    private Integer plays;

    @ApiModelProperty(value = "综合排序")
    private Long compositeOrders;

    @ApiModelProperty(value = "最热排序")
    private Long hotOrders;

    @ApiModelProperty(value = "最新排序")
    private Long newOrders;

    @ApiModelProperty(value = "状态:草稿-draft,审核中-under_review,疑似-suspected,发布-released,未通过-fail")
    private String status;

    @ApiModelProperty(value = "发布时间")
    private LocalDateTime releasedAt;

    @ApiModelProperty(value = "作者昵称")
    private String nickname;

    @ApiModelProperty(value = "作者头像")
    private String avatar;

    @ApiModelProperty(value = "时间描述")
    private String timeDescription;

    @ApiModelProperty(value = "作品基础信息")
    private BaseSection baseSection;
}
