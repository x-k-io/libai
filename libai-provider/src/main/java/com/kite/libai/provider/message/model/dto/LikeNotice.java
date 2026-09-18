package com.kite.libai.provider.message.model.dto;

import java.io.Serializable;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode(callSuper = true)
public class LikeNotice extends BaseNotice implements Serializable {
    private static final long serialVersionUID = 1L;

    @Schema(description = "点赞人id")
    private Long accountId;

    @Schema(description = "文章id")
    private Long articleId;
}
