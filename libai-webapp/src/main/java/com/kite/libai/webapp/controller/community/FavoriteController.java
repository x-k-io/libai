package com.kite.libai.webapp.controller.community;

import com.kite.libai.core.result.Result;
import com.kite.libai.provider.community.model.request.FavoriteRequest;
import com.kite.libai.provider.community.presenter.FavoritePresenter;
import com.kite.libai.security.annotation.KitePermission;

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
@RequestMapping(value = "/api/app/v1/favorites")
public class FavoriteController {

    private final FavoritePresenter favoritePresenter;

    @PostMapping
    @KitePermission
    public Result<Boolean> favorite(
            @Valid @RequestBody FavoriteRequest request) {
        boolean res = favoritePresenter.favorites(request);
        return Result.success(res);
    }

    @DeleteMapping
    @KitePermission
    public Result<Boolean> cancel(@RequestParam Long entryId) {
        boolean res = favoritePresenter.cancel(entryId);
        return Result.success(res);
    }
}
