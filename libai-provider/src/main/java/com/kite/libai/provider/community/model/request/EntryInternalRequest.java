package com.kite.libai.provider.community.model.request;

import java.io.Serializable;
import java.time.LocalDateTime;

import lombok.Data;

@Data
public class EntryInternalRequest implements Serializable {
        private Long accountId;

    private String entityType;

    private Long entityId;

    private Long circleId;

    private Long placeId;

    private String channels;

    private String topics;

    private Integer likes;

    private Integer replies;

    private Integer browses;

    private Integer forwards;

    private Integer favorites;

    private Integer plays;

    private Long compositeOrders;

    private Long hotOrders;

    private Long newOrders;

    private LocalDateTime releasedAt;

    private LocalDateTime createdAt;

    private LocalDateTime updatedAt;

}
