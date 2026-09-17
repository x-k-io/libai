package com.kite.libai.provider.tripartite.feign.model;

import java.io.Serializable;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class WeChatInfo implements Serializable {

    @NoArgsConstructor
    @AllArgsConstructor
    @Data
    public static class Watermark {
        private String appid;
        private String timestamp;
    }

    private String openId;
    private String nickName;
    private Integer gender;
    private String city;
    private String province;
    private String country;
    private String avatarUrl;
    private String unionId;
    private Watermark watermark;
}
