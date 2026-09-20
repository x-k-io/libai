package com.kite.libai.core.properties;

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
