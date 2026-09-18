package com.kite.libai.provider.community.model.request;

import java.io.Serializable;

import lombok.Data;

@Data
public class LikeRequest implements Serializable {
        private String entityType;

    private Long entityId;

    private Long entryId;
}
