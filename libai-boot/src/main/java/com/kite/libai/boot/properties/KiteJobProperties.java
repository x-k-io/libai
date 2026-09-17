package com.kite.libai.boot.properties;

import org.springframework.boot.context.properties.ConfigurationProperties;

import lombok.Data;

@Data
@ConfigurationProperties("kite.job")
public class KiteJobProperties {


    /**
     * 是否开启定时任务
     */
    private boolean enabled = Boolean.FALSE;
}
