package com.kite.libai.provider.community.model.request;

import java.io.Serializable;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

@Data
@Schema(name = "LikeRequest", description = "点赞")
public class LikeRequest implements Serializable {
    private static final long serialVersionUID = 1L;

    @Schema(description = "实体类型")
    private String entityType;

    @Schema(description = "实体id")
    private Long entityId;

    @Schema(description = "作品id")
    private Long entryId;
}
