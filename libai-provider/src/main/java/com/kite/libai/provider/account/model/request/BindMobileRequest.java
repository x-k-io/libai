package com.kite.libai.provider.account.model.request;

import lombok.Data;

import java.io.Serializable;

@Data
public class BindMobileRequest implements Serializable {
    private String ticket;
    private String mobile;
    private String code;
}
