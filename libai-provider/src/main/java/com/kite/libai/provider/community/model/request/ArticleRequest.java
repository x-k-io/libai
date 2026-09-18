package com.kite.libai.provider.community.model.request;

import java.io.Serializable;

import lombok.Data;

@Data
public class ArticleRequest implements Serializable {
        private Long accountId;

    private String type;

    private String authorType;

    private String title;

    private String subtitle;

    private String cover;

    private String picture;

    private String excerpt;

    private String detail;

    private String video;

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
}
