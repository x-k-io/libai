package com.kite.libai.provider.account.model.request;

import java.io.Serializable;

import lombok.Data;

@Data
public class TripartiteAppRequest implements Serializable {
        private String type;

    private String name;

    private String appCode;

    private String appId;

    private String appSecret;

    private String mchId;

    private String appKey;

    private Boolean isPlatform;
}
