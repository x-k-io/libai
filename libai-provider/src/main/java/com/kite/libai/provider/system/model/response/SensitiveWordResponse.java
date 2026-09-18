package com.kite.libai.provider.system.model.response;

import java.io.Serializable;
import java.time.LocalDateTime;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

@Data
@Schema(name = "SensitiveWordResponse", description = "敏感词")
public class SensitiveWordResponse implements Serializable {
    private static final long serialVersionUID = 1L;

    @Schema(description = "主键")
    private Long id;

    @Schema(description = "类型")
    private String type;

    @Schema(description = "词语")
    private String word;

    @Schema(description = "是否生效")
    private Boolean status;

    @Schema(description = "创建时间")
    private LocalDateTime createdAt;

    @Schema(description = "创建人")
    private String createdBy;

    @Schema(description = "更新时间")
    private LocalDateTime updatedAt;

    @Schema(description = "更新人")
    private String updatedBy;

}
