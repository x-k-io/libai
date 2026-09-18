package com.kite.libai.provider.message.model.request;

import java.io.Serializable;

import jakarta.validation.constraints.NotNull;

import lombok.Data;

@Data
public class ChatRequest implements Serializable {
        @NotNull
    private Long friendId;
}
