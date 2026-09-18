package com.kite.libai.provider.message.model.response;

import java.io.Serializable;
import java.time.LocalDateTime;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

@Data
@Schema(name = "ChatResponse", description = "会话")
public class ChatResponse implements Serializable {
    private static final long serialVersionUID = 1L;

    @Schema(description = "主键")
    private Long id;

    @Schema(description = "好友id")
    private Long accountId;

    @Schema(description = "好友昵称")
    private String nickname;

    @Schema(description = "好友头像")
    private String avatar;

    @Schema(description = "关系")
    private String relation;

    @Schema(description = "未读消息条数")
    private Integer unread;

    @Schema(description = "最后一条消息id")
    private Long lastMsgId;

    @Schema(description = "最后一条消息的内容")
    private String lastContent;

    @Schema(description = "最后一条消息时间")
    private LocalDateTime lastedAt;

    @Schema(description = "创建时间")
    private LocalDateTime createdAt;
}
