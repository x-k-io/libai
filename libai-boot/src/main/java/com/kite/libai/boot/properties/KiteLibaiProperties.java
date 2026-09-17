package com.kite.libai.boot.properties;

import org.springframework.boot.context.properties.ConfigurationProperties;

import lombok.Data;

@Data
@ConfigurationProperties("kite.libai")
public class KiteLibaiProperties {


    /**
     * 审核员角色
     */
    private Long reviewerRoleId = 1293792194569768962L;
    /**
     * appCode
     */
    private String appCode = "112";

    /**
     * 是否开启审核
     */
    private boolean auditEnabled = Boolean.FALSE;
}
