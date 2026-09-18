package com.kite.libai.provider.community.model.response;

import java.time.LocalDateTime;

import lombok.Data;

@Data
public class TopicResponse {

    private Long id;

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
