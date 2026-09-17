package com.kite.libai.provider.community.model.response;

import java.io.Serializable;
import java.time.LocalDateTime;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

@Data
@ApiModel(value = "FriendResponse", description = "好友信息")
public class FriendResponse implements Serializable {
    private static final long serialVersionUID = 1L;

    @ApiModelProperty(value = "主键")
    private Long id;

    @ApiModelProperty(value = "账号id")
    private Long accountId;

    @ApiModelProperty(value = "好友账号id")
    private Long friendId;

    @ApiModelProperty(value = "是否好友")
    private Boolean friend;

    @ApiModelProperty(value = "创建时间")
    private LocalDateTime createdAt;

    @ApiModelProperty(value = "昵称")
    private String nickname;

    @ApiModelProperty(value = "头像")
    private String avatar;

    @ApiModelProperty(value = "昵称首字母")
    private String initials;

    @ApiModelProperty(value = "好友粉丝数")
    private int follows;

}
