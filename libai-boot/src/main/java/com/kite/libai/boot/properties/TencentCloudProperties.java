package com.kite.libai.boot.properties;

import org.springframework.boot.context.properties.ConfigurationProperties;

import lombok.Data;

@Data
@ConfigurationProperties("kite.tencent")
public class TencentCloudProperties {


    private boolean enabled = Boolean.FALSE;

    /**
     * secretId
     */
    private String secretId = "";

    /**
     * secretKey
     */
    private String secretKey = "";
}
