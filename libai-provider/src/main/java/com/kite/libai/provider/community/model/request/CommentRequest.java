package com.kite.libai.provider.community.model.request;

import java.io.Serializable;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

@Data
@Schema(name = "CommentRequest", description = "评论")
public class CommentRequest implements Serializable {
    private static final long serialVersionUID = 1L;

    @Schema(description = "是否审核", hidden = true)
    private Boolean audit;

    @Schema(description = "评论人id")
    private Long accountId;

    @Schema(description = "作品id")
    private Long entryId;

    @Schema(description = "内容")
    private String content;
}
