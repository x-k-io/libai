package com.kite.libai.provider.community.model.response;

import java.io.Serializable;
import java.util.List;

import lombok.Data;

@Data
public class RecommendResponse implements Serializable {

    private List<RecommendFriendResponse> recommendFriends;

    private List<EntryResponse> recommendedEntries;
}
