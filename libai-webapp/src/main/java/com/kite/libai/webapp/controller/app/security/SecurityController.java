package com.kite.libai.webapp.controller.app.security;

import com.kite.libai.core.model.KiteAccount;
import com.kite.libai.provider.account.model.request.AccountLogoutRequest;
import com.kite.libai.provider.account.model.request.LoginSendCodeRequest;
import com.kite.libai.provider.account.model.request.NonLoginSendCodeRequest;
import com.kite.libai.provider.account.model.request.LoginRequest;
import com.kite.libai.provider.account.model.request.RefreshAccessTokenRequest;
import com.kite.libai.provider.account.model.response.AccountTokenVo;
import com.kite.libai.provider.account.service.AccountSecurityService;
import com.kite.libai.provider.account.service.VerifyCodeService;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import jakarta.validation.Valid;

@Slf4j
@Validated
@RestController
@AllArgsConstructor
@RequestMapping(value = "/api/app/v1/security")
public class SecurityController {

    private final AccountSecurityService accountSecurityService;
    private final VerifyCodeService verifyCodeService;

    @PostMapping("/login")
    public AccountTokenVo login(@Valid @RequestBody LoginRequest loginRequest) {
        return accountSecurityService.login(loginRequest);
    }


    @PostMapping("/refreshAccessToken")
    public AccountTokenVo refreshAccessToken(@Valid @RequestBody RefreshAccessTokenRequest request) {
        return accountSecurityService.refreshAccessToken(request);
    }


    @PostMapping("/logout")
    public Boolean logout(@Valid @RequestBody AccountLogoutRequest request) {
        accountSecurityService.logout(request);
        return Boolean.TRUE;
    }

    @GetMapping("/auth")
    public KiteAccount getKiteAccount() {
        return accountSecurityService.getKiteAccount();
    }


    @PostMapping(value = "/nonLogin/sendCode")
    public Boolean nonLoginSendCode(@Valid @RequestBody NonLoginSendCodeRequest request) {
        return verifyCodeService.nonLoginSendCode(request);
    }

    @PostMapping(value = "/login/sendCode")
    public Boolean loginSendCode(@Valid @RequestBody LoginSendCodeRequest request) {
        return verifyCodeService.loginSendCode(request);
    }
}
