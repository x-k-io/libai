package com.kite.libai.provider.message.model.request;

import java.io.Serializable;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

@Data
@Schema(name = "MessageRequest", description = "消息")
public class MessageRequest implements Serializable {
    private static final long serialVersionUID = 1L;

    @Schema(description = "消息类型")
    private String type;

    @Schema(description = "消息内容")
    private String content;

}
