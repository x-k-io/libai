package com.kite.libai.provider.community.model.response;

import java.io.Serializable;
import java.time.LocalDateTime;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

@Data
@ApiModel(value = "FriendAdminResponse", description = "好友信息")
public class FriendAdminResponse implements Serializable {
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

}
