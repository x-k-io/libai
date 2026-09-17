package com.kite.libai.provider.message.model.response.notice;

import java.io.Serializable;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode(callSuper = true)
public class ForwardNoticeResponse extends BaseNoticeResponse implements Serializable {
    private static final long serialVersionUID = 1L;

    @ApiModelProperty(value = "转发人id")
    private Long accountId;

    @ApiModelProperty(value = "转发人昵称")
    private String nickname;

    @ApiModelProperty(value = "转发人头像")
    private String avatar;

    @ApiModelProperty(value = "关系 stranger:陌生人 follower:关注 friend:好友")
    private String relation;

    @ApiModelProperty(value = "文章id")
    private Long articleId;

    @ApiModelProperty(value = "文章标题")
    private String title;

    @ApiModelProperty(value = "封面图")
    private String cover;
}
