package com.kite.libai.provider.message.model.response.notice;

import java.io.Serializable;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode(callSuper = true)
public class FollowerNoticeResponse extends BaseNoticeResponse implements Serializable {
    private static final long serialVersionUID = 1L;

    @Schema(description = "关注人id")
    private Long accountId;

    @Schema(description = "关注人昵称")
    private String nickname;

    @Schema(description = "关注人头像")
    private String avatar;

    @Schema(description = "关系 stranger:陌生人 follower:关注 friend:好友")
    private String relation;
}
