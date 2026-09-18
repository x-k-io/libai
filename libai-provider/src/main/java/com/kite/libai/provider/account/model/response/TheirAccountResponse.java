package com.kite.libai.provider.account.model.response;

import lombok.Data;
import lombok.EqualsAndHashCode;

import java.io.Serializable;

@Data
@EqualsAndHashCode(callSuper = true)
public class TheirAccountResponse extends AccountResponse implements Serializable {

    private boolean follower;

    private boolean blacklist;
}
