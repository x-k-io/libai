package com.kite.libai.provider.community.presenter;

import java.time.LocalDateTime;
import java.time.ZoneOffset;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;

import com.kite.libai.boot.cache.LibaiRedisTemplate;
import com.kite.libai.provider.account.model.entity.Account;
import com.kite.libai.provider.community.assemble.EntryAssemble;
import com.kite.libai.provider.community.model.entity.Entry;
import com.kite.libai.provider.community.model.entity.Friend;
import com.kite.libai.provider.community.model.entity.UserSocial;
import com.kite.libai.provider.community.model.response.EntryResponse;
import com.kite.libai.provider.community.model.response.RecommendFriendResponse;
import com.kite.libai.provider.community.model.response.RecommendResponse;
import com.kite.libai.provider.community.service.FriendService;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Component;

import org.apache.commons.collections4.CollectionUtils;
import com.kite.libai.boot.cache.CacheKeys;
import com.kite.libai.provider.account.enums.AccountStatus;
import com.kite.libai.provider.account.service.AccountService;
import com.kite.libai.provider.community.service.EntryService;
import com.kite.libai.provider.community.service.UserSocialService;

import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Component
@AllArgsConstructor
public class UserRecommendPresenter {

    private final FriendService friendService;

    private final EntryAssemble entryAssemble;

    private final EntryService entryService;

    private final AccountService accountService;

    private final UserSocialService userSocialService;

    private final LibaiRedisTemplate libaiRedisTemplate;

    public RecommendResponse recommend(Long accountId) {
        List<RecommendFriendResponse> recommendFriends = recommendFriends(accountId);
        RecommendResponse recommendResponse = new RecommendResponse();
        recommendResponse.setRecommendFriends(recommendFriends);
        List<Friend> followers = friendService.getFollowers(accountId);
        if (CollectionUtils.isEmpty(followers) && CollectionUtils.isNotEmpty(recommendFriends)) {
            List<Long> accountIds =
                    recommendFriends.stream().map(RecommendFriendResponse::getAccountId).collect(Collectors.toList());
            // xx天前
            LocalDateTime time = LocalDateTime.now().plusDays(-15);
            List<Entry> entries = entryService.batchGet(accountIds, time);
            List<EntryResponse> responses = entryAssemble.toResponse(entries);
            recommendResponse.setRecommendedEntries(responses);
        }
        return recommendResponse;
    }


    public List<RecommendFriendResponse> recommendFriends(Long accountId) {
        // 查询推荐人
        String key = CacheKeys.USER_RECOMMEND_KEY.get().getKey();
        Boolean has = libaiRedisTemplate.hasKey(key);
        if (has == null || !has) {
            init();
            return new ArrayList<>();
        }
        Long lastId = null;
        int limit = 100;
        List<Long> accountIds;
        Set<Long> followers;
        while (true) {
            long start = 0;
            if (lastId != null) {
                Long rank = libaiRedisTemplate.opsForZSet().rank(key, String.valueOf(lastId));
                if (rank != null) {
                    start = rank + 1;
                }
            }
            long end = start + limit - 1;
            Set<String> values = libaiRedisTemplate.opsForZSet().range(key, start, end);
            if (CollectionUtils.isEmpty(values)) {
                return new ArrayList<>();
            }
            accountIds = values.stream().map(Long::valueOf).collect(Collectors.toList());
            followers = friendService.followers(accountId, accountIds);
            if (CollectionUtils.isNotEmpty(followers) && followers.size() == accountIds.size()) {
                lastId = accountIds.get(accountIds.size() - 1);
            } else {
                break;
            }
        }
        // 封装推荐人信息
        List<Account> accounts = accountService.batchGetByIds(accountIds);
        Set<Long> finalFollowers = followers;
        return accounts.stream().filter(x -> {
            if (finalFollowers.contains(x.getId())) {
                return Boolean.FALSE;
            }
            if (x.getId().equals(accountId)) {
                return Boolean.FALSE;
            }
            if (!x.getStatus().equals(AccountStatus.NORMAL.getStatus())) {
                return Boolean.FALSE;
            }
            return Boolean.TRUE;
        }).map(x -> {
            RecommendFriendResponse response = new RecommendFriendResponse();
            response.setAccountId(x.getId());
            response.setNickname(x.getNickname());
            response.setAvatar(x.getAvatar());
            return response;
        }).collect(Collectors.toList());
    }

    /**
     * 初始化推荐列表
     */
    public void init() {
        int pageNum = 1;
        int pageSize = 100;
        while (true) {
            List<Account> accounts = accountService.getValidAccounts(pageNum, pageSize);
            if (CollectionUtils.isNotEmpty(accounts)) {
                List<Long> accountIds = accounts.stream().map(Account::getId).collect(Collectors.toList());
                Map<Long, UserSocial> userSocials = userSocialService.batchGet(accountIds);
                accounts.forEach(x -> {
                    UserSocial userSocial = userSocials.get(x.getId());
                    if (userSocial != null) {
                        this.addRecommend(x.getId(), userSocial.getLastReleasedAt());
                    }
                });
                pageNum++;
            } else {
                break;
            }
        }
    }

    /**
     * 添加用户到推荐列表
     *
     * @param accountId  accountId
     * @param releasedAt releasedAt
     */
    @Async
    public void addRecommend(Long accountId, LocalDateTime releasedAt) {
        if (releasedAt == null) {
            return;
        }
        String key = CacheKeys.USER_RECOMMEND_KEY.get().getKey();
        libaiRedisTemplate.opsForZSet().add(key, String.valueOf(accountId),
                releasedAt.toInstant(ZoneOffset.UTC).toEpochMilli() * -1);
    }

    /**
     * 从推荐列表移出用户
     *
     * @param accountId accountId
     */
    @Async
    public void removeRecommend(Long accountId) {
        if (accountId == null) {
            return;
        }
        String key = CacheKeys.USER_RECOMMEND_KEY.get().getKey();
        libaiRedisTemplate.opsForZSet().remove(key, String.valueOf(accountId));
    }
}
