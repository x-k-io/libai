package com.kite.libai.provider.account.model.response;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.io.Serializable;

@Data
@EqualsAndHashCode(callSuper = true)
public class TheirAccountResponse extends AccountResponse implements Serializable {

    @Schema(description = "是否关注")
    private boolean follower;

    @Schema(description = "是否拉黑")
    private boolean blacklist;
}
