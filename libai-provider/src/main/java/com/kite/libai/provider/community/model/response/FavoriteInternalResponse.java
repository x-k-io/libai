package com.kite.libai.provider.community.model.response;

import java.time.LocalDateTime;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

@Data
@ApiModel(value = "FavoriteInternalResponse", description = "收藏")
public class FavoriteInternalResponse {

    @ApiModelProperty(value = "主键")
    private Long id;

    @ApiModelProperty(value = "用户id")
    private Long accountId;

    @ApiModelProperty(value = "实体类型")
    private String entityType;

    @ApiModelProperty(value = "实体id")
    private Long entityId;

    @ApiModelProperty(value = "作品id")
    private Long entryId;

    @ApiModelProperty(value = "创建时间")
    private LocalDateTime createdAt;

}
