package com.kite.libai.common.context;

import com.kite.libai.common.model.KiteAccount;
import lombok.Data;

import java.util.Locale;

@Data
public class RequestContext {
    private String token;
    private String deviceId;
    private String nonce;
    private Long timestamp;
    private String sign;
    private String traceId;
    private String appName;
    private String appVersion;
    private String timeZone;
    private String ip;
    private Locale locale;
    private String userAgent;
    private Long accountId;
    private KiteAccount account;
}
