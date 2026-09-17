package com.kite.libai.provider.community.model.entity;

import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

import java.io.Serializable;
import java.time.LocalDateTime;

@Data
@TableName("t_biz_entry")
public class Entry implements Serializable {

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

    private LocalDateTime createdAt;

    private LocalDateTime updatedAt;
}
