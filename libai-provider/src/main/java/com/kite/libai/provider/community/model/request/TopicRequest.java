package com.kite.libai.provider.community.model.request;

import java.io.Serializable;
import java.time.LocalDateTime;

import lombok.Data;

@Data
public class TopicRequest implements Serializable {
        private String category;

    private String icon;

    private String name;

    private String description;

    private Long orders;

    private Integer reads;

    private Integer mentions;

    private Integer authors;

    private LocalDateTime createdAt;

    private LocalDateTime updatedAt;

}
