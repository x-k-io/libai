package com.kite.libai.provider.message.model.response.notice;

import java.io.Serializable;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode(callSuper = true)
public class SystemNoticeResponse extends BaseNoticeResponse implements Serializable {
    private static final long serialVersionUID = 1L;

    @ApiModelProperty(value = "标题")
    private String title;

    @ApiModelProperty(value = "副标题")
    private String subtitle;

    @ApiModelProperty(value = "封面")
    private String cover;

    @ApiModelProperty(value = "节选")
    private String excerpt;

    @ApiModelProperty(value = "详情")
    private String detail;
}
