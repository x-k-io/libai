package com.kite.libai.webapp.controller.community;

import com.kite.libai.core.context.RequestContextUtils;
import com.kite.libai.core.result.PageResult;
import com.kite.libai.core.result.Result;
import com.kite.libai.provider.community.model.response.EntryResponse;
import com.kite.libai.provider.community.presenter.EntryPresenter;
import com.kite.libai.provider.community.presenter.FollowerFeedPresenter;
import com.kite.libai.security.annotation.KitePermission;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
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

    @Operation(summary = "查询作品详情信息", description = "查询作品详情信息")
    @GetMapping(value = "/{id}")
    public Result<EntryResponse> getById(@PathVariable Long id) {
        EntryResponse response = entryPresenter.getEntry(id);
        return Result.success(response);
    }

    @Operation(summary = "发现页==>分页查询作品")
    @GetMapping(value = "/explore")
    public PageResult<EntryResponse> explore(
            @RequestParam String orderType,
            @RequestParam(required = false, defaultValue = "1") Integer pageNum,
            @RequestParam(required = false, defaultValue = "10") Integer pageSize) {
        return entryPresenter.explore(orderType, pageNum, pageSize);
    }

    @GetMapping(value = "/location")
    public PageResult<EntryResponse> location(
            @Parameter(description = "城市编码") String city,
            @Parameter(description = "排序类型") String orderType,
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
            @Parameter(description = "圈子id") Long circleId,
            @Parameter(description = "排序类型") String orderType,
            @RequestParam(required = false, defaultValue = "1") Integer pageNum,
            @RequestParam(required = false, defaultValue = "10") Integer pageSize) {
        return entryPresenter.circles(circleId, orderType, pageNum, pageSize);
    }

    @GetMapping(value = "/channels")
    public PageResult<EntryResponse> channels(
            @Parameter(description = "频道id") Long channelId,
            @Parameter(description = "排序类型") String orderType,
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
            @Parameter(description = "用户id") @RequestParam Long accountId,
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
