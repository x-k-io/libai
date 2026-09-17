package com.kite.libai.provider.community.model.request;

import java.io.Serializable;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

@Data
@ApiModel(value = "CommentRequest", description = "评论")
public class CommentRequest implements Serializable {
    private static final long serialVersionUID = 1L;

    @ApiModelProperty(value = "是否审核", hidden = true)
    private Boolean audit;

    @ApiModelProperty(value = "评论人id")
    private Long accountId;

    @ApiModelProperty(value = "作品id")
    private Long entryId;

    @ApiModelProperty(value = "内容")
    private String content;
}
