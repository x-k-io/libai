package com.kite.libai.provider.community.model.request;

import java.io.Serializable;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

@Data
@Schema(name = "ReplyRequest", description = "回复")
public class ReplyRequest implements Serializable {
    private static final long serialVersionUID = 1L;

    @Schema(description = "是否审核", hidden = true)
    private Boolean audit;

    @Schema(description = "评论人id")
    private Long accountId;

    @Schema(description = "作品id")
    private Long entryId;

    @Schema(description = "评论id")
    private Long commentId;

    @Schema(description = "被回复者id")
    private Long toAccountId;

    @Schema(description = "内容")
    private String content;
}
