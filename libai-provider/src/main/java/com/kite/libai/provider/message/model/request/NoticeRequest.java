package com.kite.libai.provider.message.model.request;

import java.io.Serializable;
import java.time.LocalDateTime;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

@Data
@Schema(name = "NoticeRequest", description = "通知")
public class NoticeRequest implements Serializable {
    private static final long serialVersionUID = 1L;

    @Schema(description = "通知类型")
    private String type;

    @Schema(description = "接收人id")
    private Long receiveId;

    @Schema(description = "通知内容")
    private String content;

    @Schema(description = "创建时间")
    private LocalDateTime createdAt;

}
