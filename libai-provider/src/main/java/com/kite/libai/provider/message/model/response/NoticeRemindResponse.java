package com.kite.libai.provider.message.model.response;

import java.io.Serializable;
import java.time.LocalDateTime;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

@Data
@Schema(name = "NoticeRemindResponse", description = "通知")
public class NoticeRemindResponse implements Serializable {
    private static final long serialVersionUID = 1L;

    @Schema(description = "主键")
    private Long id;

    @Schema(description = "用户id")
    private Long accountId;

    @Schema(description = "系统通知")
    private Integer system;

    @Schema(description = "点赞")
    private Integer likes;

    @Schema(description = "转发")
    private Integer forwards;

    @Schema(description = "评论")
    private Integer replies;

    @Schema(description = "关注")
    private Integer follows;

    @Schema(description = "创建时间")
    private LocalDateTime createdAt;

}
