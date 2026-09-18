package com.kite.libai.admin.controller.security;

import com.kite.libai.core.model.KiteAccount;
import com.kite.libai.core.result.Result;
import com.kite.libai.provider.account.model.request.RefreshAccessTokenRequest;
import com.kite.libai.provider.throne.model.request.AdminLoginRequest;
import com.kite.libai.provider.throne.model.request.UserLogoutRequest;
import com.kite.libai.provider.throne.model.vo.UserTokenVo;
import com.kite.libai.provider.throne.service.UserSecurityService;
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
@RequestMapping(value = "/api/admin/v1/security")
public class AdminSecurityController {

    private final UserSecurityService userSecurityService;


    @PostMapping("/login")
    public UserTokenVo adminLogin(@Valid @RequestBody AdminLoginRequest loginRequest) {
        return userSecurityService.adminLogin(loginRequest);
    }

    @PostMapping("/refreshAccessToken")
    public UserTokenVo refreshAccessToken(@Valid @RequestBody RefreshAccessTokenRequest request) {
        return userSecurityService.refreshAccessToken(request);
    }

    @PostMapping("/logout")
    public boolean logout(@Valid @RequestBody UserLogoutRequest request) {
        userSecurityService.logout(request);
        return Boolean.TRUE;
    }

    @GetMapping("/auth")
    public KiteAccount getKiteAccount() {
        return userSecurityService.getKiteUser();
    }
}
