package com.kite.libai.boot.properties;

import org.springframework.boot.context.properties.ConfigurationProperties;

import lombok.Data;

@Data
@ConfigurationProperties("kite.aliyun")
public class AliYunProperties {


    /**
     * accessKeyId
     */
    private String accessKeyId = "";

    /**
     * accessKeySecret
     */
    private String accessKeySecret = "";

    /**
     * endpoint
     */
    private String endpoint = "";

    /**
     * bucketUrl
     */
    private String bucketUrl = "";

    /**
     * bucketName
     */
    private String bucketName = "";

    // 短信发送配置
    /**
     * 签名
     */
    private String signName = "";
}
