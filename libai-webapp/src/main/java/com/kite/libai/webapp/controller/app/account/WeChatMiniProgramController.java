package com.kite.libai.webapp.controller.app.account;

import com.kite.libai.common.result.Result;
import com.kite.libai.provider.account.model.request.AppOauthRequest;
import com.kite.libai.provider.account.model.request.ExchangeBindRequest;
import com.kite.libai.provider.account.model.request.WeChatAuthRequest;
import com.kite.libai.provider.account.model.request.WeChatBindRequest;
import com.kite.libai.provider.account.model.response.AppOauthResponse;
import com.kite.libai.provider.account.model.response.ExchangeBindResponse;
import com.kite.libai.provider.account.presenter.WeChatPresenter;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import jakarta.validation.Valid;


@Slf4j
@Validated
@RestController
@AllArgsConstructor
@RequestMapping(value = "/api/app/v1/we-chat/mini-programs")
public class WeChatMiniProgramController {

    private final WeChatPresenter weChatPresenter;

    // 1、通过微信code进行认证，云端将通过code从微信获取到能够解密的session_key
    @PostMapping("/oauth")
    public Result<AppOauthResponse> miniOauth(
            @Valid @RequestBody AppOauthRequest appOauthRequest) {
        return weChatPresenter.miniOauth(appOauthRequest);
    }

    // 2、携带小程序获取的的用户隐私信息的加密数据进行认证，云端这时可以拿到对应的明文数据unionId，如果绑定直接登录，如果未绑定进行绑定处理
    @PostMapping("/auth")
    public Result<AppOauthResponse> miniProgramAuth(
            @Valid @RequestBody WeChatAuthRequest weChatAuthRequest) {
        return weChatPresenter.miniProgramAuth(weChatAuthRequest);
    }

    // 3、绑定微信，两种方式，手机号验证码，微信小程序获取的手机号密文，未绑定直接绑定，返回可以登录的凭证，如果已经绑定提示用户是否换绑
    @PostMapping("/bind")
    public Result<ExchangeBindResponse> bind(
            @Valid @RequestBody WeChatBindRequest weChatBindRequest) {
        return weChatPresenter.bind(weChatBindRequest);
    }

    // 4、确认换绑微信，如果换绑返回可以登录的凭证，如果不换绑不能登录
    @PostMapping("/exchange")
    public Result<ExchangeBindResponse> exchangeBindMiniProgram(
            @Valid @RequestBody ExchangeBindRequest exchangeBindRequest) {
        return weChatPresenter.exchangeBindMiniProgram(exchangeBindRequest);
    }
}
