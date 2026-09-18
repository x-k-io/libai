package com.kite.libai.provider.community.model.response;

import java.util.List;

import lombok.Data;

@Data
public class TopicCategoryResponse {

    private String category;

    private String name;

    private List<TopicResponse> topics;
}
