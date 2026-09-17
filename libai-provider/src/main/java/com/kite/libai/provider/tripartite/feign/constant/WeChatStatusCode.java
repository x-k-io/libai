package com.kite.libai.provider.tripartite.feign.constant;



public class WeChatStatusCode {

    public static final int SUCCESS_CODE = 0;
    /**
     * 不合法的调用凭证,获取 access_token时AppSecret错误，或者access_token无效
     */
    public static final int INVALID_CREDENTIAL_CODE = 40001;
    /**
     * 不合法的AppID
     */
    public static final int APP_ID_INVALID_CODE = 40013;
    /**
     * 授权码无效
     */
    public static final int CODE_INVALID_CODE = 40029;
    /**
     * 授权码已被使用
     */
    public static final int CODE_BEEN_USED_CODE = 40163;
    /**
     * 不合法的refresh_token
     */
    public static final int REFRESH_TOKEN_INVALID_CODE = 40030;
    /**
     * 频率限制
     */
    public static final int FREQUENCY_LIMIT_CODE = 45011;
    /**
     * 系统繁忙
     */
    public static final int SYSTEM_BUSY_CODE = -1;


}
