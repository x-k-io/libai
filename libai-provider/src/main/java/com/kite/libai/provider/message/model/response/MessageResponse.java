package com.kite.libai.provider.message.model.response;

import java.io.Serializable;
import java.time.LocalDateTime;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

@Data
@ApiModel(value = "MessageResponse", description = "消息")
public class MessageResponse implements Serializable {
    private static final long serialVersionUID = 1L;

    @ApiModelProperty(value = "主键")
    private Long id;

    @ApiModelProperty(value = "消息类型")
    private String type;

    @ApiModelProperty(value = "会话id")
    private Long chatId;

    @ApiModelProperty(value = "发送者id")
    private Long accountId;

    @ApiModelProperty(value = "消息内容")
    private String content;

    @ApiModelProperty(value = "创建时间")
    private LocalDateTime createdAt;

    @ApiModelProperty(value = "发送者昵称")
    private String nickname;

    @ApiModelProperty(value = "发送者头像")
    private String avatar;

}
