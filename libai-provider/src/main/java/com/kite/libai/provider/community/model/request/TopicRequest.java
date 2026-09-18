package com.kite.libai.provider.community.model.request;

import java.io.Serializable;
import java.time.LocalDateTime;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

@Data
@Schema(name = "TopicRequest", description = "话题")
public class TopicRequest implements Serializable {
    private static final long serialVersionUID = 1L;

    @Schema(description = "分类")
    private String category;

    @Schema(description = "图标")
    private String icon;

    @Schema(description = "名称")
    private String name;

    @Schema(description = "描述")
    private String description;

    @Schema(description = "排序")
    private Long orders;

    @Schema(description = "阅读数")
    private Integer reads;

    @Schema(description = "讨论数")
    private Integer mentions;

    @Schema(description = "作者人数")
    private Integer authors;

    @Schema(description = "创建时间")
    private LocalDateTime createdAt;

    @Schema(description = "更新时间")
    private LocalDateTime updatedAt;

}
