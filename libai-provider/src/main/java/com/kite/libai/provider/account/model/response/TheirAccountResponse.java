package com.kite.libai.provider.account.model.response;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.io.Serializable;

@Data
@EqualsAndHashCode(callSuper = true)
public class TheirAccountResponse extends AccountResponse implements Serializable {

    @ApiModelProperty(value = "是否关注")
    private boolean follower;

    @ApiModelProperty(value = "是否拉黑")
    private boolean blacklist;
}
