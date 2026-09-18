package com.kite.libai.provider.community.model.response;

import java.util.List;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

@Data
@Schema(name = "TopicCategoryResponse", description = "话题分类")
public class TopicCategoryResponse {

    @Schema(description = "分类")
    private String category;

    @Schema(description = "名称")
    private String name;

    @Schema(description = "话题")
    private List<TopicResponse> topics;
}
