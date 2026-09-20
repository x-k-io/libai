package com.kite.libai.webapp.controller.app.community;

import com.kite.libai.core.context.RequestContextUtils;
import com.kite.libai.provider.community.model.response.BlacklistResponse;
import com.kite.libai.provider.community.presenter.BlacklistPresenter;
import com.kite.libai.webapp.annotation.KitePermission;

import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@Slf4j
@Validated
@RestController
@AllArgsConstructor
@RequestMapping(value = "/api/app/v1/blacklists")
public class BlacklistController {

    private final BlacklistPresenter blacklistPresenter;

    @PostMapping
    @KitePermission
    public void blacklist(@RequestParam Long theirId) {
        blacklistPresenter.blacklist(RequestContextUtils.getAccountId(), theirId);
    }

    @DeleteMapping
    @KitePermission
    public void cancel(@RequestParam Long theirId) {
        blacklistPresenter.cancel(RequestContextUtils.getAccountId(), theirId);
    }

    @GetMapping
    @KitePermission
    public List<BlacklistResponse> getBlacklist(
            @RequestParam(required = false, defaultValue = "1") Integer pageNum,
            @RequestParam(required = false, defaultValue = "10") Integer pageSize) {
        return blacklistPresenter.getBlacklist(RequestContextUtils.getAccountId());
    }
}
