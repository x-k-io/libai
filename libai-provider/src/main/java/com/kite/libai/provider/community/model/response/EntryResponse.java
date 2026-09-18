package com.kite.libai.provider.community.model.response;

import java.time.LocalDateTime;

import com.kite.libai.provider.community.model.common.BaseSection;

import lombok.Data;

@Data
public class EntryResponse {

    private Long id;

    private Long authorId;

    private String type;

    private String authorType;

    private String entityType;

    private Long entityId;

    private Long channelId;

    private Long circleId;

    private Long placeId;

    private String longitude;

    private String latitude;

    private String country;

    private String province;

    private String city;

    private String district;

    private String place;

    private Integer likes;

    private Integer replies;

    private Integer browses;

    private Integer favorites;

    private Integer plays;

    private Long compositeOrders;

    private Long hotOrders;

    private Long newOrders;

    private String status;

    private LocalDateTime releasedAt;

    private String nickname;

    private String avatar;

    private Boolean liked;

    private Boolean favorite;

    private String timeDescription;

    private BaseSection baseSection;
}
