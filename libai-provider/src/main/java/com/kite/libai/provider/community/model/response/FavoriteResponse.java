package com.kite.libai.provider.community.model.response;

import java.time.LocalDateTime;

import lombok.Data;

@Data
public class FavoriteResponse {

    private Long id;

    private Long accountId;

    private String entityType;

    private Long entityId;

    private Long entryId;

    private LocalDateTime createdAt;

}
