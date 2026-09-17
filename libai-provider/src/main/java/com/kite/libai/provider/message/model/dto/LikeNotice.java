package com.kite.libai.provider.message.model.dto;

import java.io.Serializable;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode(callSuper = true)
public class LikeNotice extends BaseNotice implements Serializable {
    private static final long serialVersionUID = 1L;

    @ApiModelProperty(value = "点赞人id")
    private Long accountId;

    @ApiModelProperty(value = "文章id")
    private Long articleId;
}
