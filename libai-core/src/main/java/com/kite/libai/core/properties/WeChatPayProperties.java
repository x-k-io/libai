package com.kite.libai.core.properties;

import org.springframework.boot.context.properties.ConfigurationProperties;

import lombok.Data;

@Data
@ConfigurationProperties("kite.wx.pay")
public class WeChatPayProperties {

    /**
     * payNotifyUrl
     */
    private String payNotifyUrl = "https://api.cyberspace.link/kite-fly-api/v1-0/open/wx-pay/pay-notify";


    /**
     * refundNotifyUrl
     */
    private String refundNotifyUrl = "https://api.cyberspace.link/kite-fly-api/v1-0/open/wx-pay/refund-notify";
}
