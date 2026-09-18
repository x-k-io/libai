package com.kite.libai.provider.message.model.response.notice;

import java.io.Serializable;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode(callSuper = true)
public class LikeNoticeResponse extends BaseNoticeResponse implements Serializable {
    private static final long serialVersionUID = 1L;

    @Schema(description = "点赞人id")
    private Long accountId;

    @Schema(description = "点赞人昵称")
    private String nickname;

    @Schema(description = "点赞人头像")
    private String avatar;

    @Schema(description = "关系 stranger:陌生人 follower:关注 friend:好友")
    private String relation;

    @Schema(description = "文章id")
    private Long articleId;

    @Schema(description = "文章标题")
    private String title;

    @Schema(description = "封面图")
    private String cover;
}
