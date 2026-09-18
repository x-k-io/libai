package com.kite.libai.provider.community.model.request;

import java.io.Serializable;
import java.time.LocalDateTime;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

@Data
@Schema(name = "EntryInternalRequest", description = "作品")
public class EntryInternalRequest implements Serializable {
    private static final long serialVersionUID = 1L;

    @Schema(description = "作者id")
    private Long accountId;

    @Schema(description = "实体类型")
    private String entityType;

    @Schema(description = "实体id")
    private Long entityId;

    @Schema(description = "圈子id")
    private Long circleId;

    @Schema(description = "地点id")
    private Long placeId;

    @Schema(description = "频道")
    private String channels;

    @Schema(description = "话题")
    private String topics;

    @Schema(description = "点赞数")
    private Integer likes;

    @Schema(description = "回复数")
    private Integer replies;

    @Schema(description = "浏览数")
    private Integer browses;

    @Schema(description = "转发数")
    private Integer forwards;

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

    @Schema(description = "发布时间")
    private LocalDateTime releasedAt;

    @Schema(description = "创建时间")
    private LocalDateTime createdAt;

    @Schema(description = "更新时间")
    private LocalDateTime updatedAt;

}
