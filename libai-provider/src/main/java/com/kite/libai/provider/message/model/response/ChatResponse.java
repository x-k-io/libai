package com.kite.libai.provider.message.model.response;

import java.io.Serializable;
import java.time.LocalDateTime;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

@Data
@ApiModel(value = "ChatResponse", description = "会话")
public class ChatResponse implements Serializable {
    private static final long serialVersionUID = 1L;

    @ApiModelProperty(value = "主键")
    private Long id;

    @ApiModelProperty(value = "好友id")
    private Long accountId;

    @ApiModelProperty(value = "好友昵称")
    private String nickname;

    @ApiModelProperty(value = "好友头像")
    private String avatar;

    @ApiModelProperty(value = "关系")
    private String relation;

    @ApiModelProperty(value = "未读消息条数")
    private Integer unread;

    @ApiModelProperty(value = "最后一条消息id")
    private Long lastMsgId;

    @ApiModelProperty(value = "最后一条消息的内容")
    private String lastContent;

    @ApiModelProperty(value = "最后一条消息时间")
    private LocalDateTime lastedAt;

    @ApiModelProperty(value = "创建时间")
    private LocalDateTime createdAt;
}
