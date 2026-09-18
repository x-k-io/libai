package com.kite.libai.provider.community.model.response;

import java.io.Serializable;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

@Data
@Schema(name = "RecommendFriendResponse", description = "推荐好友信息")
public class RecommendFriendResponse implements Serializable {
    private static final long serialVersionUID = 1L;

    @Schema(description = "账号id")
    private Long accountId;

    @Schema(description = "昵称")
    private String nickname;

    @Schema(description = "头像")
    private String avatar;
}
