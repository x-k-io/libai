package com.kite.libai.provider.message.model.response;

import java.io.Serializable;
import java.time.LocalDateTime;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

@Data
@ApiModel(value = "NoticeRemindResponse", description = "通知")
public class NoticeRemindResponse implements Serializable {
    private static final long serialVersionUID = 1L;

    @ApiModelProperty(value = "主键")
    private Long id;

    @ApiModelProperty(value = "用户id")
    private Long accountId;

    @ApiModelProperty(value = "系统通知")
    private Integer system;

    @ApiModelProperty(value = "点赞")
    private Integer likes;

    @ApiModelProperty(value = "转发")
    private Integer forwards;

    @ApiModelProperty(value = "评论")
    private Integer replies;

    @ApiModelProperty(value = "关注")
    private Integer follows;

    @ApiModelProperty(value = "创建时间")
    private LocalDateTime createdAt;

}
