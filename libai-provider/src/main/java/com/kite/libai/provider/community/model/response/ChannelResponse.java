package com.kite.libai.provider.community.model.response;

import java.time.LocalDateTime;

import lombok.Data;

@Data
public class ChannelResponse {

    private Long id;

    private String category;

    private String name;

    private Long orders;

    private LocalDateTime createdAt;

    private LocalDateTime updatedAt;

}
