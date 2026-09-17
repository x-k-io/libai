package com.kite.libai.provider.account.model.request;

import java.io.Serializable;

import lombok.Data;

@Data
public class SendCodeRequest implements Serializable {

    private String type;

    private String mobile;

    private String deviceId;

    private String ip;
}
