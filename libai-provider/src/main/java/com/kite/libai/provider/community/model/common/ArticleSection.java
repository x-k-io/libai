package com.kite.libai.provider.community.model.common;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode(callSuper = true)
public class ArticleSection extends BaseSection {

    @Schema(description = "主键")
    private Long id;

    @Schema(description = "作者id")
    private Long authorId;

    @Schema(description = "内容类型")
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
    private String pictures;

    @Schema(description = "节选")
    private String excerpt;

    @Schema(description = "内容")
    private String detail;

    @Schema(description = "视频连接")
    private String video;

    @Schema(description = "视频时长")
    private Integer videoLength;
}
