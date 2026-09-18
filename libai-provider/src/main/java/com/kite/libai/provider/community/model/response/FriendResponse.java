package com.kite.libai.provider.community.model.response;

import java.io.Serializable;
import java.time.LocalDateTime;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

@Data
@Schema(name = "FriendResponse", description = "好友信息")
public class FriendResponse implements Serializable {
    private static final long serialVersionUID = 1L;

    @Schema(description = "主键")
    private Long id;

    @Schema(description = "账号id")
    private Long accountId;

    @Schema(description = "好友账号id")
    private Long friendId;

    @Schema(description = "是否好友")
    private Boolean friend;

    @Schema(description = "创建时间")
    private LocalDateTime createdAt;

    @Schema(description = "昵称")
    private String nickname;

    @Schema(description = "头像")
    private String avatar;

    @Schema(description = "昵称首字母")
    private String initials;

    @Schema(description = "好友粉丝数")
    private int follows;

}
