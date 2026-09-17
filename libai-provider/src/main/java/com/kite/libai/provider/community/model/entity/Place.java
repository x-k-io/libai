package com.kite.libai.provider.community.model.entity;

import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

import java.io.Serializable;
import java.time.LocalDateTime;

@Data
@TableName("t_biz_place")
public class Place implements Serializable {

    private Long id;

    private String type;

    private String name;

    private String subtitle;

    private String cover;

    private String labels;

    private String telephone;

    private String description;

    private String longitude;

    private String latitude;

    private String country;

    private String province;

    private String city;

    private String district;

    private String address;

    private String status;

    private LocalDateTime createdAt;

    private LocalDateTime updatedAt;
}
