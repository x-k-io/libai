package com.kite.libai.webapp.controller.app.community;

import com.kite.libai.core.context.RequestContextUtils;
import com.kite.libai.core.result.PageResult;
import com.kite.libai.core.result.Result;
import com.kite.libai.provider.community.model.response.FriendResponse;
import com.kite.libai.provider.community.model.response.RecommendFriendResponse;
import com.kite.libai.provider.community.model.response.RecommendResponse;
import com.kite.libai.provider.community.presenter.FriendPresenter;
import com.kite.libai.provider.community.presenter.UserRecommendPresenter;
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
import java.util.Map;

@Slf4j
@Validated
@RestController
@AllArgsConstructor
@RequestMapping(value = "/api/app/v1/friends")
public class FriendController {

    private final FriendPresenter friendPresenter;

    private final UserRecommendPresenter userRecommendPresenter;

    @PostMapping
    @KitePermission
    public Result<Boolean> follower(@RequestParam Long friendId) {
        friendPresenter.follower(RequestContextUtils.getAccountId(), friendId);
        return Result.success();
    }


    @DeleteMapping
    @KitePermission
    public Result<Boolean> cancelFollower(
            @RequestParam Long friendId) {
        friendPresenter.cancelFollower(RequestContextUtils.getAccountId(), friendId);
        return Result.success();
    }

    @GetMapping(value = "/followers")
    public List<FriendResponse> getFollowers(
            @RequestParam Long accountId) {
        return friendPresenter.getFollowers(accountId);
    }

    @GetMapping(value = "/following")
    public PageResult<FriendResponse> getFollowing(
            @RequestParam Long accountId,
            @RequestParam(required = false, defaultValue = "1") Integer pageNum,
            @RequestParam(required = false, defaultValue = "10") Integer pageSize) {
        return friendPresenter.getFollowing(accountId, pageNum, pageSize);
    }

    @GetMapping(value = "/address-books")
    public Map<String, List<FriendResponse>> getAddressBookList() {
        return friendPresenter.getAddressBookList(RequestContextUtils.getAccountId());
    }

    @GetMapping(value = "/recommend")
    @KitePermission
    public List<RecommendFriendResponse> recommend() {
        return userRecommendPresenter.recommendFriends(RequestContextUtils.getAccountId());
    }

    @GetMapping(value = "/recommend1")
    @KitePermission
    public RecommendResponse recommendV1() {
        return userRecommendPresenter.recommend(RequestContextUtils.getAccountId());
    }
}
