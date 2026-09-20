package com.kite.libai.webapp.controller.app.community;

import com.kite.libai.common.result.Result;
import com.kite.libai.provider.community.model.request.LikeRequest;
import com.kite.libai.provider.community.presenter.LikePresenter;
import com.kite.libai.boot.annotation.KitePermission;

import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import jakarta.validation.Valid;

@Slf4j
@Validated
@RestController
@AllArgsConstructor
@RequestMapping(value = "/api/app/v1/likes")
public class LikeController {

    private final LikePresenter likePresenter;

    @PostMapping
    @KitePermission
    public Result<Boolean> like(@Valid @RequestBody LikeRequest likeRequest) {
        boolean res = likePresenter.like(likeRequest);
        return Result.success(res);
    }

    @DeleteMapping
    @KitePermission
    public Result<Boolean> cancel(@RequestParam Long entryId) {
        boolean res = likePresenter.cancel(entryId);
        return Result.success(res);
    }
}
