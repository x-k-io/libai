package com.kite.libai.provider.account.model.request;

import lombok.Data;

import java.io.Serializable;

@Data
public class LoginSendCodeRequest implements Serializable {
    private String type;
}
