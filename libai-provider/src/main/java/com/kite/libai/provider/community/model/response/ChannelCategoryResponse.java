package com.kite.libai.provider.community.model.response;

import java.util.List;

import lombok.Data;

@Data
public class ChannelCategoryResponse {

    private String category;

    private String name;

    private List<ChannelResponse> channels;
}
