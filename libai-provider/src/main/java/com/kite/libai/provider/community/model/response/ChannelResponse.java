package com.kite.libai.provider.community.model.response;

import java.time.LocalDateTime;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

@Data
@Schema(name = "ChannelResponse", description = "频道")
public class ChannelResponse {

    @Schema(description = "主键")
    private Long id;

    @Schema(description = "分类")
    private String category;

    @Schema(description = "名称")
    private String name;

    @Schema(description = "排序")
    private Long orders;

    @Schema(description = "创建时间")
    private LocalDateTime createdAt;

    @Schema(description = "更新时间")
    private LocalDateTime updatedAt;

}
