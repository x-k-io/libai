package com.kite.libai.provider.tripartite.service;

import java.util.Map;

import org.springframework.stereotype.Service;

import com.aliyun.dysmsapi20170525.models.SendSmsRequest;
import com.aliyun.dysmsapi20170525.models.SendSmsResponse;
import com.aliyun.teaopenapi.models.Config;
import com.kite.libai.core.utils.JsonUtils;
import com.kite.libai.boot.properties.AliYunProperties;

import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
@AllArgsConstructor
public class AliSendMessageService {

    private final AliYunProperties aliYunProperties;

    /**
     * 发送短消息验证码
     *
     * @param mobile        mobile
     * @param templateCode  templateCode
     * @param templateParam templateParam
     * @return Boolean
     */
    public Boolean sendMessage(String mobile, String templateCode, Map<String, String> templateParam) {
        com.aliyun.dysmsapi20170525.Client client = createClient();
        // 组装请求对象
        SendSmsRequest request = new SendSmsRequest();
        // 必填:待发送手机号。支持以逗号分隔的形式进行批量调用，批量上限为1000个手机号码,批量调用相对于单条调用及时性稍有延迟,验证码类型的短信推荐使用单条调用的方式；发送国际/港澳台消息时，接收号码格式为国际区号+号码，如“85200000000”
        request.setPhoneNumbers(mobile);
        // 必填:短信签名-可在短信控制台中找到
        request.setSignName(aliYunProperties.getSignName());
        // 必填:短信模板-可在短信控制台中找到，发送国际/港澳台消息时，请使用国际/港澳台短信模版
        request.setTemplateCode(templateCode);
        request.setTemplateParam(JsonUtils.toJson(templateParam));
        try {
            SendSmsResponse sendSmsResponse = client.sendSms(request);
            if (sendSmsResponse.getBody().code != null && sendSmsResponse.getBody().code.equals("OK")) {
                return Boolean.TRUE;
            } else {
                log.error("短信发送失败===> body:{}", sendSmsResponse.getBody());
            }
        } catch (Exception e) {
            log.error("发送短信失败 mobile: {}", mobile);
        }
        return Boolean.FALSE;
    }

    public com.aliyun.dysmsapi20170525.Client createClient() {
        Config config = new Config()
                // 您的AccessKey ID
                .setAccessKeyId(aliYunProperties.getAccessKeyId())
                // 您的AccessKey Secret
                .setAccessKeySecret(aliYunProperties.getAccessKeySecret());
        config.endpoint = "dysmsapi.aliyuncs.com";
        try {
            return new com.aliyun.dysmsapi20170525.Client(config);
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }
}
