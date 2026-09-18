package com.kite.libai.provider.community.model.response;

import java.time.LocalDateTime;

import com.kite.libai.provider.community.model.common.BaseSection;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

@Data
@Schema(name = "EntryInternalResponse", description = "作品")
public class EntryInternalResponse {

    @Schema(description = "主键")
    private Long id;

    @Schema(description = "作者id")
    private Long authorId;

    @Schema(description = "内容类型")
    private String type;

    @Schema(description = "作者类型")
    private String authorType;

    @Schema(description = "实体类型")
    private String entityType;

    @Schema(description = "实体id")
    private Long entityId;

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

    @Schema(description = "国家")
    private String country;

    @Schema(description = "省")
    private String province;

    @Schema(description = "市")
    private String city;

    @Schema(description = "区县")
    private String district;

    @Schema(description = "位置")
    private String place;

    @Schema(description = "点赞数")
    private Integer likes;

    @Schema(description = "回复数")
    private Integer replies;

    @Schema(description = "浏览数")
    private Integer browses;

    @Schema(description = "收藏数")
    private Integer favorites;

    @Schema(description = "播放数")
    private Integer plays;

    @Schema(description = "综合排序")
    private Long compositeOrders;

    @Schema(description = "最热排序")
    private Long hotOrders;

    @Schema(description = "最新排序")
    private Long newOrders;

    @Schema(description = "状态:草稿-draft,审核中-under_review,疑似-suspected,发布-released,未通过-fail")
    private String status;

    @Schema(description = "发布时间")
    private LocalDateTime releasedAt;

    @Schema(description = "作者昵称")
    private String nickname;

    @Schema(description = "作者头像")
    private String avatar;

    @Schema(description = "时间描述")
    private String timeDescription;

    @Schema(description = "作品基础信息")
    private BaseSection baseSection;
}
