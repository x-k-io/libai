package com.kite.libai.provider.tripartite.feign;

import com.kite.libai.common.utils.OkHttpUtils;
import com.kite.libai.provider.tripartite.feign.model.WeChatMiniProgramPages;
import org.springframework.stereotype.Service;

import java.io.IOException;

@Service
public class WechatRemoteServiceImpl implements WeChatRemoteService {

    @Override
    public String code2session(String appId, String appSecret, String code, String grantType) {
        try {
           return OkHttpUtils.get("https://api.weixin.qq.com/sns/jscode2session?appid=" + appId + "&secret=" + appSecret + "&js_code=" + code + "&grant_type=" + grantType);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public String getAccessToken(String grantType, String appId, String appSecret) {
        try {
            return OkHttpUtils.get("https://api.weixin.qq.com/cgi-bin/token?grant_type=" + grantType + "&appid=" + appId + "&secret=" + appSecret);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public String getUserInfo(String accessToken, String jsCode, String grantType) {
        try {
            return OkHttpUtils.get("https://api.weixin.qq.com/sns/userinfo?access_token=" + accessToken + "&openid=" + jsCode + "&lang=zh_CN");
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public String submitPages(String accessToken, WeChatMiniProgramPages pages) {
        return null;
    }
}
