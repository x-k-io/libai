package com.kite.libai.provider.message.model.response.notice;

import java.io.Serializable;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode(callSuper = true)
public class CommentNoticeResponse extends BaseNoticeResponse implements Serializable {
    private static final long serialVersionUID = 1L;


    @Schema(description = "评论人id")
    private Long accountId;

    @Schema(description = "评论人昵称")
    private String nickname;

    @Schema(description = "评论人头像")
    private String avatar;

    @Schema(description = "评论id")
    private Long commentId;

    @Schema(description = "评论内容")
    private String content;

    @Schema(description = "文章id")
    private Long articleId;

    @Schema(description = "文章标题")
    private String title;

    @Schema(description = "封面图")
    private String cover;

    @Schema(description = "评论是否已删除")
    private Boolean deleted;
}
