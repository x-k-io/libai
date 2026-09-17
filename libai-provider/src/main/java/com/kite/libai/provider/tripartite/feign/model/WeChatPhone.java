package com.kite.libai.provider.tripartite.feign.model;

import java.io.Serializable;

import lombok.Data;

@Data
public class WeChatPhone implements Serializable {
    /**
     * 用户绑定的手机号（国外手机号会有区号）
     */
    private String phoneNumber;
    /**
     * 没有区号的手机号
     */
    private String purePhoneNumber;
    /**
     * 区号
     */
    private int countryCode;
}
