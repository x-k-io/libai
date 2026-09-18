package com.kite.libai.provider.community.model.response;

import java.io.Serializable;
import java.time.LocalDateTime;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

@Data
@Schema(name = "FriendAdminResponse", description = "好友信息")
public class FriendAdminResponse implements Serializable {
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

}
