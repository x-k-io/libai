package com.kite.libai.webapp.controller.app.community;

import com.kite.libai.common.context.RequestContextUtils;
import com.kite.libai.common.result.PageResult;
import com.kite.libai.common.result.Result;
import com.kite.libai.provider.community.model.response.EntryResponse;
import com.kite.libai.provider.community.presenter.EntryPresenter;
import com.kite.libai.provider.community.presenter.FollowerFeedPresenter;
import com.kite.libai.boot.annotation.KitePermission;

import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@Slf4j
@Validated
@RestController
@AllArgsConstructor
@RequestMapping(value = "/api/app/v1/entries")
public class EntryController {

    private final EntryPresenter entryPresenter;

    private final FollowerFeedPresenter followerFeedPresenter;

    @GetMapping(value = "/{id}")
    public Result<EntryResponse> getById(@PathVariable Long id) {
        EntryResponse response = entryPresenter.getEntry(id);
        return Result.success(response);
    }

    @GetMapping(value = "/explore")
    public PageResult<EntryResponse> explore(
            @RequestParam String orderType,
            @RequestParam(required = false, defaultValue = "1") Integer pageNum,
            @RequestParam(required = false, defaultValue = "10") Integer pageSize) {
        return entryPresenter.explore(orderType, pageNum, pageSize);
    }

    @GetMapping(value = "/location")
    public PageResult<EntryResponse> location(
            String city,
            String orderType,
            @RequestParam(required = false, defaultValue = "1") Integer pageNum,
            @RequestParam(required = false, defaultValue = "10") Integer pageSize) {
        return entryPresenter.location(city, orderType, pageNum, pageSize);
    }


    @GetMapping(value = "/followers")
    @KitePermission
    public Result<List<EntryResponse>> location(
            @RequestParam(required = false) Long lastId,
            @RequestParam(required = false, defaultValue = "10") Integer pageSize) {
        List<EntryResponse> responses = followerFeedPresenter.getArticlesByFollowers(RequestContextUtils.getAccountId(), lastId, pageSize);
        return Result.success(responses);
    }

    @GetMapping(value = "/circles")
    public PageResult<EntryResponse> circles(
            Long circleId,
            String orderType,
            @RequestParam(required = false, defaultValue = "1") Integer pageNum,
            @RequestParam(required = false, defaultValue = "10") Integer pageSize) {
        return entryPresenter.circles(circleId, orderType, pageNum, pageSize);
    }

    @GetMapping(value = "/channels")
    public PageResult<EntryResponse> channels(
            Long channelId,
            String orderType,
            @RequestParam(required = false, defaultValue = "1") Integer pageNum,
            @RequestParam(required = false, defaultValue = "10") Integer pageSize) {
        return entryPresenter.channels(channelId, orderType, pageNum, pageSize);
    }

    @GetMapping(value = "/me")
    @KitePermission
    public PageResult<EntryResponse> me(
            @RequestParam(required = false, defaultValue = "1") Integer pageNum,
            @RequestParam(required = false, defaultValue = "10") Integer pageSize) {
        return entryPresenter.me(RequestContextUtils.getAccountId(), pageNum, pageSize);
    }

    @GetMapping(value = "/their")
    public PageResult<EntryResponse> their(
            @RequestParam Long accountId,
            @RequestParam(required = false, defaultValue = "1") Integer pageNum,
            @RequestParam(required = false, defaultValue = "10") Integer pageSize) {
        return entryPresenter.their(accountId, pageNum, pageSize);
    }

    @GetMapping(value = "/browse")
    @KitePermission
    public PageResult<EntryResponse> browses(
            @RequestParam(required = false, defaultValue = "1") Integer pageNum,
            @RequestParam(required = false, defaultValue = "10") Integer pageSize) {
        return entryPresenter.browse(RequestContextUtils.getAccountId(), pageNum, pageSize);
    }

    @GetMapping(value = "/favorite")
    @KitePermission
    public PageResult<EntryResponse> favorites(
            @RequestParam(required = false, defaultValue = "1") Integer pageNum,
            @RequestParam(required = false, defaultValue = "10") Integer pageSize) {
        return entryPresenter.favorite(RequestContextUtils.getAccountId(), pageNum, pageSize);
    }
}
