package com.kite.libai.provider.community.model.response;

import java.io.Serializable;
import java.util.List;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

@Data
@ApiModel(value = "RecommendResponse", description = "推荐信息")
public class RecommendResponse implements Serializable {

    @ApiModelProperty(value = "推荐好友")
    private List<RecommendFriendResponse> recommendFriends;

    @ApiModelProperty(value = "推荐文章")
    private List<EntryResponse> recommendedEntries;
}
