package com.kite.libai.provider.community.model.request;

import java.io.Serializable;
import java.time.LocalDateTime;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

@Data
@Schema(name = "PlaceInternalRequest", description = "位置")
public class PlaceInternalRequest implements Serializable {
    private static final long serialVersionUID = 1L;

    @Schema(description = "类型")
    private String type;

    @Schema(description = "名称")
    private String name;

    @Schema(description = "副标题")
    private String subtitle;

    @Schema(description = "封面")
    private String cover;

    @Schema(description = "标签")
    private String labels;

    @Schema(description = "电话")
    private String telephone;

    @Schema(description = "描述")
    private String description;

    @Schema(description = "经度")
    private String longitude;

    @Schema(description = "纬度")
    private String latitude;

    @Schema(description = "国家")
    private String country;

    @Schema(description = "省")
    private String province;

    @Schema(description = "市")
    private String city;

    @Schema(description = "区")
    private String district;

    @Schema(description = "详细地址")
    private String address;

    @Schema(description = "状态")
    private String status;

    @Schema(description = "创建时间")
    private LocalDateTime createdAt;

    @Schema(description = "更新时间")
    private LocalDateTime updatedAt;

}
