package com.kite.libai.provider.community.model.response;

import java.io.Serializable;
import java.util.List;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

@Data
@Schema(name = "RecommendResponse", description = "推荐信息")
public class RecommendResponse implements Serializable {

    @Schema(description = "推荐好友")
    private List<RecommendFriendResponse> recommendFriends;

    @Schema(description = "推荐文章")
    private List<EntryResponse> recommendedEntries;
}
