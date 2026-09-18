package com.kite.libai.provider.message.model.request;

import java.io.Serializable;

import jakarta.validation.constraints.NotNull;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

@Data
@Schema(name = "ChatRequest", description = "会话")
public class ChatRequest implements Serializable {
    private static final long serialVersionUID = 1L;

    @NotNull
    @Schema(description = "好友id")
    private Long friendId;
}
