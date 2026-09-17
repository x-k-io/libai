package com.kite.libai.provider.message.model.response.notice;

import java.io.Serializable;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode(callSuper = true)
public class CommentNoticeResponse extends BaseNoticeResponse implements Serializable {
    private static final long serialVersionUID = 1L;


    @ApiModelProperty(value = "评论人id")
    private Long accountId;

    @ApiModelProperty(value = "评论人昵称")
    private String nickname;

    @ApiModelProperty(value = "评论人头像")
    private String avatar;

    @ApiModelProperty(value = "评论id")
    private Long commentId;

    @ApiModelProperty(value = "评论内容")
    private String content;

    @ApiModelProperty(value = "文章id")
    private Long articleId;

    @ApiModelProperty(value = "文章标题")
    private String title;

    @ApiModelProperty(value = "封面图")
    private String cover;

    @ApiModelProperty(value = "评论是否已删除")
    private Boolean deleted;
}
