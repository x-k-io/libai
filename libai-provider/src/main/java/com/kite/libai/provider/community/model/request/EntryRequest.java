package com.kite.libai.provider.community.model.request;

import java.io.Serializable;
import java.time.LocalDateTime;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

@Data
@ApiModel(value = "EntryRequest", description = "作品")
public class EntryRequest implements Serializable {
    private static final long serialVersionUID = 1L;

    @ApiModelProperty(value = "作者id")
    private Long accountId;

    @ApiModelProperty(value = "实体类型")
    private String entityType;

    @ApiModelProperty(value = "实体id")
    private Long entityId;

    @ApiModelProperty(value = "圈子id")
    private Long circleId;

    @ApiModelProperty(value = "地点id")
    private Long placeId;

    @ApiModelProperty(value = "频道")
    private String channels;

    @ApiModelProperty(value = "话题")
    private String topics;

    @ApiModelProperty(value = "点赞数")
    private Integer likes;

    @ApiModelProperty(value = "回复数")
    private Integer replies;

    @ApiModelProperty(value = "浏览数")
    private Integer browses;

    @ApiModelProperty(value = "转发数")
    private Integer forwards;

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

    @ApiModelProperty(value = "发布时间")
    private LocalDateTime releasedAt;

    @ApiModelProperty(value = "创建时间")
    private LocalDateTime createdAt;

    @ApiModelProperty(value = "更新时间")
    private LocalDateTime updatedAt;

}
