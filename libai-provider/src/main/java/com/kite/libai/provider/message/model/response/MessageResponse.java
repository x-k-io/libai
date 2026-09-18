package com.kite.libai.provider.message.model.response;

import java.io.Serializable;
import java.time.LocalDateTime;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

@Data
@Schema(name = "MessageResponse", description = "消息")
public class MessageResponse implements Serializable {
    private static final long serialVersionUID = 1L;

    @Schema(description = "主键")
    private Long id;

    @Schema(description = "消息类型")
    private String type;

    @Schema(description = "会话id")
    private Long chatId;

    @Schema(description = "发送者id")
    private Long accountId;

    @Schema(description = "消息内容")
    private String content;

    @Schema(description = "创建时间")
    private LocalDateTime createdAt;

    @Schema(description = "发送者昵称")
    private String nickname;

    @Schema(description = "发送者头像")
    private String avatar;

}
