package com.kite.libai.provider.community.model.request;

import java.io.Serializable;
import java.util.List;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

@Data
@Schema(name = "UpdateOrdersRequest", description = "更新排序请求体")
public class UpdateOrdersRequest implements Serializable {
    private static final long serialVersionUID = 1L;

    @Schema(description = "ids")
    private List<Long> ids;
}
