package com.kite.libai.webapp.controller.app.account;

import com.kite.libai.core.context.RequestContextUtils;
import com.kite.libai.provider.account.model.request.BindMobileRequest;
import com.kite.libai.provider.account.model.request.GenUpdateMobileTicketRequest;
import com.kite.libai.provider.account.model.request.UpdateAccountRequest;
import com.kite.libai.provider.account.model.request.ResetPasswordRequest;
import com.kite.libai.provider.account.model.request.UpdatePasswordRequest;
import com.kite.libai.provider.account.model.response.AccountResponse;
import com.kite.libai.provider.account.presenter.AccountPresenter;
import com.kite.libai.webapp.annotation.KitePermission;

import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import jakarta.validation.Valid;

@Slf4j
@Validated
@RestController
@AllArgsConstructor
@RequestMapping(value = "/api/app/v1/accounts")
public class AccountController {

    private final AccountPresenter accountPresenter;

    @KitePermission
    @PutMapping(value = "/updateAccountInfo")
    public Boolean updateAccountInfo(@Valid @RequestBody UpdateAccountRequest updateAccountRequest) {
        Long accountId = RequestContextUtils.getAccountId();
        return accountPresenter.update(accountId, updateAccountRequest);
    }

    @PostMapping("/resetPassword")
    public Boolean resetPassword(@Valid @RequestBody ResetPasswordRequest request) {
        return accountPresenter.resetPassword(request);
    }

    @KitePermission
    @PutMapping(value = "/updatePassword")
    public Boolean updatePassword(@Valid @RequestBody UpdatePasswordRequest request) {
        return accountPresenter.updatePassword(request);
    }

    @KitePermission
    @PutMapping(value = "/genUpdateMobileTicket")
    public String genUpdateMobileTicket(@Valid @RequestBody GenUpdateMobileTicketRequest request) {
        return accountPresenter.genUpdateMobileTicket(request);
    }

    @KitePermission
    @PutMapping(value = "/bindMobile")
    public Boolean bindMobile(@Valid @RequestBody BindMobileRequest request) {
        return accountPresenter.bindMobile(request);
    }

    @KitePermission
    @GetMapping(value = "/me")
    public AccountResponse me() {
        Long accountId = RequestContextUtils.getAccountId();
        return accountPresenter.getAccountInfo(accountId);
    }

    @KitePermission
    @GetMapping(value = "/their")
    public AccountResponse getTheirInfo(@RequestParam Long theirId) {
        Long accountId = RequestContextUtils.getAccountId();
        return accountPresenter.getTheirInfo(accountId, theirId);
    }
}
