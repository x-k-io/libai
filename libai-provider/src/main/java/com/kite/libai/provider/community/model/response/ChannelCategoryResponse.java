package com.kite.libai.provider.community.model.response;

import java.util.List;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

@Data
@Schema(name = "ChannelCategoryResponse", description = "频道分类")
public class ChannelCategoryResponse {

    @Schema(description = "分类")
    private String category;

    @Schema(description = "名称")
    private String name;

    @Schema(description = "频道")
    private List<ChannelResponse> channels;
}
