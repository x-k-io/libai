package com.kite.libai.provider.message.model.response.notice;

import java.io.Serializable;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode(callSuper = true)
public class FollowerNoticeResponse extends BaseNoticeResponse implements Serializable {
    private static final long serialVersionUID = 1L;

    @ApiModelProperty(value = "关注人id")
    private Long accountId;

    @ApiModelProperty(value = "关注人昵称")
    private String nickname;

    @ApiModelProperty(value = "关注人头像")
    private String avatar;

    @ApiModelProperty(value = "关系 stranger:陌生人 follower:关注 friend:好友")
    private String relation;
}
