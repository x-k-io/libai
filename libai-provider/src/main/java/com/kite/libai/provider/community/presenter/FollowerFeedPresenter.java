package com.kite.libai.provider.community.presenter;

import java.time.LocalDateTime;
import java.time.ZoneOffset;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

import com.kite.libai.core.cache.LibaiRedisTemplate;
import com.kite.libai.provider.community.model.entity.Entry;
import com.kite.libai.provider.community.model.entity.Friend;
import com.kite.libai.provider.community.model.entity.UserSocial;
import com.kite.libai.provider.community.model.response.EntryResponse;
import com.kite.libai.provider.community.assemble.EntryAssemble;
import com.kite.libai.provider.community.service.UserSocialService;
import org.springframework.data.redis.core.DefaultTypedTuple;
import org.springframework.data.redis.core.ZSetOperations;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Component;

import org.apache.commons.collections4.CollectionUtils;
import com.kite.libai.core.cache.CacheKeys;
import com.kite.libai.provider.community.service.EntryService;
import com.kite.libai.provider.community.service.FriendService;

import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Component
@AllArgsConstructor
public class FollowerFeedPresenter {

    private final FriendService friendService;

    private final UserSocialService userSocialService;

    private final EntryService entryService;

    private final EntryAssemble entryAssemble;

    private final LibaiRedisTemplate libaiRedisTemplate;

    /**
     * 关注页
     *
     * @param accountId accountId
     * @param lastId    lastId
     * @param limit     limit
     * @return List<ArticleResponse>
     */
    public List<EntryResponse> getArticlesByFollowers(Long accountId, Long lastId, int limit) {
        String key = CacheKeys.FOLLOWERS_ARTICLE_KEY.get(accountId).getKey();
        Boolean has = libaiRedisTemplate.hasKey(key);
        if (has == null || !has) {
            loadArticles(accountId);
        }
        long start = 0;
        if (lastId != null) {
            Long rank = libaiRedisTemplate.opsForZSet().rank(key, String.valueOf(lastId));
            if (rank != null) {
                start = rank + 1;
            }
        }
        long end = start + limit - 1;

        Set<String> articleIds = libaiRedisTemplate.opsForZSet().range(key, start, end);
        if (CollectionUtils.isEmpty(articleIds)) {
            return new ArrayList<>();
        }
        List<Long> ids = articleIds.stream().map(Long::valueOf).collect(Collectors.toList());
        List<Entry> entries = entryService.batchGetByIdAndStatus(ids);
        if (CollectionUtils.isEmpty(entries)) {
            return new ArrayList<>();
        }
        List<Long> friendIds = entries.stream().map(Entry::getAuthorId).collect(Collectors.toList());
        Set<Long> followers = friendService.followers(accountId, friendIds);
        // 过滤掉未关注的人的帖子
        entries = entries.stream().filter(x -> followers.contains(x.getAuthorId())).collect(Collectors.toList());
        return entryAssemble.toResponse(entries);
    }

    /**
     * 长期未登录用户加载
     *
     * @param accountId accountId
     */
    private void loadArticles(Long accountId) {
        List<Friend> followers = friendService.getFollowers(accountId);
        if (CollectionUtils.isEmpty(followers)) {
            return;
        }
        // xx天前
        LocalDateTime time = LocalDateTime.now().plusDays(-15);
        List<Long> friendIds = followers.stream().map(Friend::getFriendId).distinct().collect(Collectors.toList());
        List<UserSocial> userSocials = userSocialService.batchGet(friendIds, time);
        if (CollectionUtils.isEmpty(userSocials)) {
            return;
        }
        // xx天内发布过文章的关注人
        List<Long> accountIds =
                userSocials.stream().map(UserSocial::getAccountId).distinct().collect(Collectors.toList());
        List<Entry> entries = entryService.batchGet(accountIds, time);
        addFollowerEntries(accountId, entries);
    }

    /**
     * 将内容加载至缓存
     *
     * @param accountId accountId
     * @param entries   entries
     */
    private void addFollowerEntries(Long accountId, List<Entry> entries) {
        if (CollectionUtils.isEmpty(entries)) {
            return;
        }
        String key = CacheKeys.FOLLOWERS_ARTICLE_KEY.get(accountId).getKey();
        Set<ZSetOperations.TypedTuple<String>> tuples = new HashSet<>();
        entries.forEach(x -> {
            long sort = x.getReleasedAt().toInstant(ZoneOffset.UTC).toEpochMilli();
            tuples.add(new DefaultTypedTuple<>(String.valueOf(x.getId()), (double) sort * -1));
        });
        libaiRedisTemplate.opsForZSet().add(key, tuples);
    }

    /**
     * 新增关注将文章加入到关注列表
     *
     * @param accountId accountId
     * @param friendId  friendId
     */
    @Async
    public void addFollowers(Long accountId, Long friendId) {
        // xx天前
        LocalDateTime time = LocalDateTime.now().plusDays(-15);
        List<Entry> entries = entryService.getEntries(friendId, time);
        addFollowerEntries(accountId, entries);
    }

    @Async
    public void addFollowers(Long accountId, Long entryId, Long sort) {
        List<Friend> following = friendService.getFollowing(accountId);
        if (CollectionUtils.isEmpty(following)) {
            return;
        }
        for (Friend friend : following) {
            String key = CacheKeys.FOLLOWERS_ARTICLE_KEY.get(friend.getAccountId()).getKey();
            libaiRedisTemplate.opsForZSet().add(key, String.valueOf(entryId), sort * -1);
        }
    }
}
