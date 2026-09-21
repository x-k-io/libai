package com.kite.libai.provider.account.model.request;

import java.io.Serializable;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@Data
@AllArgsConstructor
public class WeChatAuthRequest implements Serializable {

    @NotBlank
    private String appCode;

    @NotBlank(message = "st不能为空")
    private String sessionSt;

    @NotNull(message = "userInfo不能为空")
    private UserInfo userInfo;

    @NotBlank(message = "rawData不能为空")
    private String rawData;

    @NotBlank(message = "signature不能为空")
    private String signature;

    @NotBlank(message = "encryptedData不能为空")
    private String encryptedData;

    @NotBlank(message = "iv不能为空")
    private String iv;

    @NoArgsConstructor
    @AllArgsConstructor
    @Data
    public static class Watermark {
        private String appid;
        private String timestamp;
    }

    @NoArgsConstructor
    @AllArgsConstructor
    @Data
    public static class UserInfo {
        private String openId;
        private String nickName;
        private String gender;
        private String city;
        private String province;
        private String country;
        private String avatarUrl;
        private String unionId;
        private Watermark watermark;
    }
}
