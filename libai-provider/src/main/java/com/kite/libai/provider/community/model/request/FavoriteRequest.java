package com.kite.libai.provider.community.model.request;

import java.io.Serializable;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

@Data
@ApiModel(value = "FavoriteRequest", description = "收藏")
public class FavoriteRequest implements Serializable {
    private static final long serialVersionUID = 1L;

    @ApiModelProperty(value = "作品id")
    private Long entryId;

}
