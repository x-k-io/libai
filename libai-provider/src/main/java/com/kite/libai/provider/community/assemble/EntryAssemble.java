package com.kite.libai.provider.community.assemble;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.Set;
import java.util.stream.Collectors;

import com.kite.libai.core.utils.BeanUtils;
import com.kite.libai.provider.account.model.entity.Account;
import com.kite.libai.provider.community.model.common.BaseSection;
import com.kite.libai.provider.community.model.entity.Entry;
import com.kite.libai.provider.community.model.response.EntryInternalResponse;
import com.kite.libai.provider.community.model.response.EntryResponse;
import com.kite.libai.provider.community.strategy.entry.EntryProcessor;
import com.kite.libai.provider.community.strategy.factory.EntryProcessorFactory;
import org.springframework.stereotype.Component;

import com.kite.libai.core.context.RequestContextUtils;
import org.apache.commons.collections4.CollectionUtils;
import com.kite.libai.core.utils.ListUtils;
import com.kite.libai.core.utils.RelativeDateFormat;
import com.kite.libai.provider.community.enums.ArticleStatus;
import com.kite.libai.provider.account.service.AccountService;
import com.kite.libai.provider.community.service.FavoriteService;
import com.kite.libai.provider.community.service.LikeService;

import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Component
@AllArgsConstructor
public class EntryAssemble {

    private final LikeService likeService;

    private final FavoriteService favoriteService;

    private final AccountService accountService;

    private final EntryProcessorFactory entryProcessorFactory;

    public List<EntryResponse> filter(List<EntryResponse> responses) {
        if (CollectionUtils.isEmpty(responses)) {
            return responses;
        }
        Long accountId = RequestContextUtils.getAccountId();
        return responses.stream().filter(x -> {
            // 作者可以浏览自己所有状态文章
            if (accountId != null && accountId.equals(x.getAuthorId())) {
                return Boolean.TRUE;
            }
            // 已发布的文章都可以浏览
            if (ArticleStatus.RELEASED.getStatus().equals(x.getStatus())) {
                return Boolean.TRUE;
            }
            return Boolean.FALSE;
        }).collect(Collectors.toList());
    }

    public EntryResponse toResponse(Entry entry) {
        if (entry == null) {
            return null;
        }
        return toResponse(ListUtils.of(entry)).stream().findFirst().orElse(null);
    }

    public List<EntryResponse> toResponse(List<Entry> entries) {
        Long accountId = RequestContextUtils.getAccountId();
        if (CollectionUtils.isEmpty(entries)) {
            return new ArrayList<>();
        }
        List<Long> authorIds = entries.stream().map(Entry::getAuthorId).distinct().collect(Collectors.toList());
        List<Long> entryIds = entries.stream().map(Entry::getId).collect(Collectors.toList());
        Map<Long, Account> authors = accountService.batchGet(authorIds);
        Set<Long> liked = likeService.liked(accountId, entryIds);
        Set<Long> favorites = favoriteService.favorites(accountId, entryIds);
        // 作品基础信息
        Map<Long, BaseSection> sections = new HashMap<>(16);
        Map<String, List<Entry>> map = entries.stream().collect(Collectors.groupingBy(Entry::getEntityType));
        for (Map.Entry<String, List<Entry>> item : map.entrySet()) {
            String entityType = item.getKey();
            List<Long> entityIds = item.getValue().stream().map(Entry::getEntityId).collect(Collectors.toList());
            EntryProcessor entryProcessor = entryProcessorFactory.getByEntityType(entityType);
            if (entryProcessor != null) {
                Map<Long, BaseSection> subSections = entryProcessor.batchGet(entityIds);
                sections.putAll(subSections);
            }
        }
        List<EntryResponse> responses = entries.stream().map(x -> {
            BaseSection baseSection = sections.get(x.getEntityId());
            if (baseSection == null) {
                log.warn("未查询到作品基础信息 entryId:{}", x.getId());
                return null;
            }
            EntryResponse response = BeanUtils.copy(x, EntryResponse.class);
            response.setBaseSection(baseSection);
            Account account = authors.get(x.getAuthorId());
            if (account != null) {
                response.setNickname(account.getNickname());
                response.setAvatar(account.getAvatar());
            }
            response.setLiked(liked.contains(x.getId()));
            response.setFavorite(favorites.contains(x.getId()));
            if (x.getReleasedAt() != null) {
                response.setTimeDescription(RelativeDateFormat.format(x.getReleasedAt()));
            } else {
                response.setTimeDescription(RelativeDateFormat.format(x.getCreatedAt()));
            }
            return response;
        }).filter(Objects::nonNull).collect(Collectors.toList());
        return responses;
    }

    public EntryInternalResponse toInternalResponse(Entry entry) {
        if (entry == null) {
            return null;
        }
        return toInternalResponse(ListUtils.of(entry)).stream().findFirst().orElse(null);
    }

    public List<EntryInternalResponse> toInternalResponse(List<Entry> entries) {
        if (CollectionUtils.isEmpty(entries)) {
            return new ArrayList<>();
        }
        List<Long> authorIds = entries.stream().map(Entry::getAuthorId).distinct().collect(Collectors.toList());
        Map<Long, Account> authors = accountService.batchGet(authorIds);
        // 作品基础信息
        Map<Long, BaseSection> sections = new HashMap<>(16);
        Map<String, List<Entry>> map = entries.stream().collect(Collectors.groupingBy(Entry::getEntityType));
        for (Map.Entry<String, List<Entry>> item : map.entrySet()) {
            String entityType = item.getKey();
            List<Long> entityIds = item.getValue().stream().map(Entry::getEntityId).collect(Collectors.toList());
            EntryProcessor entryProcessor = entryProcessorFactory.getByEntityType(entityType);
            if (entryProcessor != null) {
                Map<Long, BaseSection> subSections = entryProcessor.batchGet(entityIds);
                sections.putAll(subSections);
            }
        }
        List<EntryInternalResponse> responses = entries.stream().map(x -> {
            BaseSection baseSection = sections.get(x.getEntityId());
            if (baseSection == null) {
                log.warn("未查询到作品基础信息 entryId:{}", x.getId());
                return null;
            }
            EntryInternalResponse response = BeanUtils.copy(x, EntryInternalResponse.class);
            response.setBaseSection(baseSection);
            Account account = authors.get(x.getAuthorId());
            if (account != null) {
                response.setNickname(account.getNickname());
                response.setAvatar(account.getAvatar());
            }
            if (x.getReleasedAt() != null) {
                response.setTimeDescription(RelativeDateFormat.format(x.getReleasedAt()));
            } else {
                response.setTimeDescription(RelativeDateFormat.format(x.getCreatedAt()));
            }
            return response;
        }).collect(Collectors.toList());
        return responses;
    }

}
