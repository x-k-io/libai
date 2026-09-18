package com.kite.libai.provider.message.model.response;

import java.io.Serializable;
import java.time.LocalDateTime;

import com.kite.libai.provider.message.model.response.notice.BaseNoticeResponse;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

@Data
@Schema(name = "NoticeResponse", description = "通知")
public class NoticeResponse implements Serializable {
    private static final long serialVersionUID = 1L;

    @Schema(description = "主键")
    private Long id;

    @Schema(description = "通知类型")
    private String type;

    @Schema(description = "接收人id")
    private Long receiveId;

    @Schema(description = "通知内容")
    private BaseNoticeResponse detail;

    @Schema(description = "创建时间")
    private LocalDateTime createdAt;

}
