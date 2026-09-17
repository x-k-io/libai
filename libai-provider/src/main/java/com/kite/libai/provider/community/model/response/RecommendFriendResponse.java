package com.kite.libai.provider.community.model.response;

import java.io.Serializable;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

@Data
@ApiModel(value = "RecommendFriendResponse", description = "推荐好友信息")
public class RecommendFriendResponse implements Serializable {
    private static final long serialVersionUID = 1L;

    @ApiModelProperty(value = "账号id")
    private Long accountId;

    @ApiModelProperty(value = "昵称")
    private String nickname;

    @ApiModelProperty(value = "头像")
    private String avatar;
}
