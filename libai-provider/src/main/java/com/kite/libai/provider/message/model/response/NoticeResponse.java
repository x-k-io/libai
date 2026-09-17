package com.kite.libai.provider.message.model.response;

import java.io.Serializable;
import java.time.LocalDateTime;

import com.kite.libai.provider.message.model.response.notice.BaseNoticeResponse;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

@Data
@ApiModel(value = "NoticeResponse", description = "通知")
public class NoticeResponse implements Serializable {
    private static final long serialVersionUID = 1L;

    @ApiModelProperty(value = "主键")
    private Long id;

    @ApiModelProperty(value = "通知类型")
    private String type;

    @ApiModelProperty(value = "接收人id")
    private Long receiveId;

    @ApiModelProperty(value = "通知内容")
    private BaseNoticeResponse detail;

    @ApiModelProperty(value = "创建时间")
    private LocalDateTime createdAt;

}
