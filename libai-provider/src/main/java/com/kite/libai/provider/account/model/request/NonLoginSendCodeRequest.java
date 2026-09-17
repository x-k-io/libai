package com.kite.libai.provider.account.model.request;

import lombok.Data;

import java.io.Serializable;

@Data
public class NonLoginSendCodeRequest implements Serializable {
    private String type;

    private String mobile;
}
