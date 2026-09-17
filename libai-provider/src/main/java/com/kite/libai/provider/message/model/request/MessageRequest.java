package com.kite.libai.provider.message.model.request;

import java.io.Serializable;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

@Data
@ApiModel(value = "MessageRequest", description = "消息")
public class MessageRequest implements Serializable {
    private static final long serialVersionUID = 1L;

    @ApiModelProperty(value = "消息类型")
    private String type;

    @ApiModelProperty(value = "消息内容")
    private String content;

}
