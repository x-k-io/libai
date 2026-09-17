package com.kite.libai.provider.message.model.request;

import java.io.Serializable;
import java.time.LocalDateTime;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

@Data
@ApiModel(value = "NoticeRequest", description = "通知")
public class NoticeRequest implements Serializable {
    private static final long serialVersionUID = 1L;

    @ApiModelProperty(value = "通知类型")
    private String type;

    @ApiModelProperty(value = "接收人id")
    private Long receiveId;

    @ApiModelProperty(value = "通知内容")
    private String content;

    @ApiModelProperty(value = "创建时间")
    private LocalDateTime createdAt;

}
