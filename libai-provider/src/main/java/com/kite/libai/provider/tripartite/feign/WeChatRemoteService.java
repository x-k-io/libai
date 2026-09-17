package com.kite.libai.provider.tripartite.feign;

import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.kite.libai.provider.tripartite.feign.model.WeChatMiniProgramPages;

public interface WeChatRemoteService {

    /**
     * 根据授权码获取sessionKey
     *
     * @param appId     appId
     * @param appSecret appSecret
     * @param code      code
     * @param grantType grantType
     * @return sessionKey
     */
    @RequestMapping(value = "/sns/jscode2session", method = RequestMethod.GET)
    String code2session(
            @RequestParam("appid") String appId,
            @RequestParam("secret") String appSecret,
            @RequestParam("js_code") String code,
            @RequestParam("grant_type") String grantType);

    /**
     * 查询 token
     *
     * @param grantType grantType
     * @param appId     appId
     * @param appSecret appSecret
     * @return accessToken
     */
    @RequestMapping(value = "/cgi-bin/token", method = RequestMethod.GET)
    String getAccessToken(@RequestParam("grant_type") String grantType, @RequestParam("appid") String appId,
                          @RequestParam("secret") String appSecret);

    /**
     * 查询 getUserInfo
     *
     * @param accessToken accessToken
     * @param jsCode      jsCode
     * @param grantType   grantType
     * @return String
     */
    @RequestMapping(value = "/cgi-bin/miniprogram/jscode2session", method = RequestMethod.GET)
    String getUserInfo(@RequestParam("access_token") String accessToken, @RequestParam("js_code") String jsCode,
                       @RequestParam("grant_type") String grantType);

    /**
     * 提交页面到微信SEO
     *
     * @param accessToken accessToken
     * @param pages       pages
     * @return
     */
    @RequestMapping(value = "/wxa/search/wxaapi_submitpages", method = RequestMethod.POST)
    String submitPages(@RequestParam("access_token") String accessToken,
                       @RequestBody WeChatMiniProgramPages pages);
}
