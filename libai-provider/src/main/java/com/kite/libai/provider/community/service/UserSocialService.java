package com.kite.libai.provider.community.service;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.function.Function;
import java.util.stream.Collectors;

import com.kite.libai.core.repository.BaseService;
import com.kite.libai.provider.community.model.entity.UserSocial;
import com.kite.libai.provider.community.repository.UserSocialRepository;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

import org.apache.commons.collections4.CollectionUtils;
import com.kite.libai.provider.community.enums.DataType;

import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
@AllArgsConstructor
public class UserSocialService extends BaseService<UserSocialRepository, UserSocial> {


    /**
     * 批量查询用户社交信息
     *
     * @param accountIds     accountIds
     * @param lastReleasedAt 最后发布时间
     * @return List<UserSocial>
     */
    public List<UserSocial> batchGet(List<Long> accountIds, LocalDateTime lastReleasedAt) {
        if (CollectionUtils.isEmpty(accountIds)) {
            return new ArrayList<>();
        }
        List<UserSocial> userSocials = this.repository.batchGetByAccountId(accountIds);
        return userSocials.stream()
                .filter(userSocial -> userSocial.getLastReleasedAt().isAfter(lastReleasedAt))
                .collect(Collectors.toList());
    }

    /**
     * 批量查询用户社交信息
     *
     * @param accountIds accountIds
     * @return Map<Long, UserSocial>
     */
    public Map<Long, UserSocial> batchGet(List<Long> accountIds) {
        List<UserSocial> list = this.repository.batchGetByAccountId(accountIds);
        if (CollectionUtils.isNotEmpty(list)) {
            return list.stream().collect(Collectors.toMap(UserSocial::getAccountId, Function.identity()));
        }
        return new HashMap<>(16);
    }


    public UserSocial getByAccountId(Long accountId) {
        return this.repository.getByAccountId(accountId);
    }

    public void init(Long accountId) {
        UserSocial userSocial = new UserSocial();
        userSocial.setAccountId(accountId);
        this.save(userSocial);
    }

    /**
     * 更新用户社交数据
     *
     * @param accountId accountId
     * @param dataType  dataType
     */
    @Async
    public void add(Long accountId, DataType dataType) {
        UserSocial userSocial = this.getByAccountId(accountId);
        if (userSocial == null) {
            return;
        }
        switch (dataType) {
            case FOLLOWING:
                userSocial.setFollowing(userSocial.getFollowing() + 1);
                break;
            case FOLLOWERS:
                userSocial.setFollowers(userSocial.getFollowers() + 1);
                break;
            case ENTRIES:
                userSocial.setEntries(userSocial.getEntries() + 1);
                userSocial.setLastReleasedAt(LocalDateTime.now());
                break;
            case REPLIES:
                userSocial.setReplies(userSocial.getReplies() + 1);
                break;
            case LIKES:
                userSocial.setLikes(userSocial.getLikes() + 1);
                break;
            case BROWSES:
                userSocial.setBrowses(userSocial.getBrowses() + 1);
                break;
            case PLAYS:
                userSocial.setPlays(userSocial.getPlays() + 1);
                break;
            default:
                break;
        }
        this.updateById(userSocial);
    }

    /**
     * 更新用户数据
     *
     * @param accountId accountId
     * @param dataType  dataType
     */
    @Async
    public void reduce(Long accountId, DataType dataType) {
        UserSocial userSocial = this.getById(accountId);
        if (userSocial == null) {
            return;
        }
        switch (dataType) {
            case FOLLOWING:
                userSocial.setFollowing(userSocial.getFollowing() - 1);
                break;
            case FOLLOWERS:
                userSocial.setFollowers(userSocial.getFollowers() - 1);
                break;
            case ENTRIES:
                userSocial.setEntries(userSocial.getEntries() - 1);
                break;
            case REPLIES:
                userSocial.setReplies(userSocial.getReplies() - 1);
                break;
            case LIKES:
                userSocial.setLikes(userSocial.getLikes() - 1);
                break;
            case BROWSES:
                userSocial.setBrowses(userSocial.getBrowses() - 1);
                break;
            case PLAYS:
                userSocial.setPlays(userSocial.getPlays() - 1);
                break;
            default:
                break;
        }
        this.updateById(userSocial);
    }
}
