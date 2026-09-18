package com.kite.libai.provider.community.model.request;

import java.io.Serializable;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

@Data
@Schema(name = "EntryAuditRequest", description = "作品审核请求体")
public class EntryAuditRequest implements Serializable {

    @Schema(description = "作者id")
    private Long authorId;

    @Schema(description = "作者类型")
    private String authorType;

    @Schema(description = "实体类型")
    private String entityType;

    @Schema(description = "实体id")
    private Long entityId;

    @Schema(description = "作品id")
    private Long entryId;
}
