package com.kite.libai.provider.account.model.request;

import java.io.Serializable;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@Data
@Schema(description = "WeChatAuthForm")
@AllArgsConstructor
public class WeChatAuthRequest implements Serializable {

    private static final long serialVersionUID = 377150381098251576L;

    @NotBlank
    @Schema(description = "appCode", required = true)
    private String appCode;

    @NotBlank(message = "st不能为空")
    @Schema(description = "微信信息", required = true)
    private String sessionSt;

    @NotNull(message = "userInfo不能为空")
    @Schema(description = "用户信息对象，不包含 openid 等敏感信息", required = true)
    private UserInfo userInfo;

    @NotBlank(message = "rawData不能为空")
    @Schema(description = "不包括敏感信息的原始数据字符串，用于计算签名", required = true)
    private String rawData;

    @NotBlank(message = "signature不能为空")
    @Schema(description = "使用 sha1( rawData + sessionkey ) 得到字符串，用于校验用户信息", required = true)
    private String signature;

    @NotBlank(message = "encryptedData不能为空")
    @Schema(description = "包括敏感数据在内的完整用户信息的加密数据", required = true)
    private String encryptedData;

    @NotBlank(message = "iv不能为空")
    @Schema(description = "加密算法的初始向量", required = true)
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
