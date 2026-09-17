package com.kite.libai.provider.community.model.request;

import java.io.Serializable;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

@Data
@ApiModel(value = "EntryAuditRequest", description = "作品审核请求体")
public class EntryAuditRequest implements Serializable {

    @ApiModelProperty(value = "作者id")
    private Long authorId;

    @ApiModelProperty(value = "作者类型")
    private String authorType;

    @ApiModelProperty(value = "实体类型")
    private String entityType;

    @ApiModelProperty(value = "实体id")
    private Long entityId;

    @ApiModelProperty(value = "作品id")
    private Long entryId;
}
