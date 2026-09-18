package com.kite.libai.provider.community.model.response;

import java.time.LocalDateTime;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

@Data
@Schema(name = "FavoriteResponse", description = "收藏")
public class FavoriteResponse {

    @Schema(description = "主键")
    private Long id;

    @Schema(description = "用户id")
    private Long accountId;

    @Schema(description = "实体类型")
    private String entityType;

    @Schema(description = "实体id")
    private Long entityId;

    @Schema(description = "作品id")
    private Long entryId;

    @Schema(description = "创建时间")
    private LocalDateTime createdAt;

}
