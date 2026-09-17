package com.kite.libai.provider.community.model.request;

import java.io.Serializable;
import java.util.List;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

@Data
@ApiModel(value = "UpdateOrdersRequest", description = "更新排序请求体")
public class UpdateOrdersRequest implements Serializable {
    private static final long serialVersionUID = 1L;

    @ApiModelProperty(value = "ids")
    private List<Long> ids;
}
