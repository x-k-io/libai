package com.kite.libai.provider.community.model.response;

import java.io.Serializable;
import java.time.LocalDateTime;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

@Data
@Schema(name = "AccountBlacklistResponse", description = "用户黑名单")
public class BlacklistResponse implements Serializable {
    private static final long serialVersionUID = 1L;

    @Schema(description = "主键")
    private Long id;

    @Schema(description = "账号id")
    private Long accountId;

    @Schema(description = "被拉黑者的id")
    private Long theirId;

    @Schema(description = "创建时间")
    private LocalDateTime createdAt;

    @Schema(description = "昵称")
    private String nickname;

    @Schema(description = "头像")
    private String avatar;
}
