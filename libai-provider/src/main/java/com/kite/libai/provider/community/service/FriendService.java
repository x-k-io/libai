package com.kite.libai.provider.community.service;

import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.function.Function;
import java.util.stream.Collectors;

import com.baomidou.mybatisplus.core.toolkit.ObjectUtils;
import com.kite.libai.core.repository.BaseService;
import com.kite.libai.common.result.PageResult;
import com.kite.libai.provider.community.model.entity.Friend;
import com.kite.libai.provider.community.repository.FriendRepository;
import org.springframework.stereotype.Service;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import org.apache.commons.collections4.CollectionUtils;

import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
@AllArgsConstructor
public class FriendService extends BaseService<FriendRepository, Friend> {

    /**
     * 批量查询好友信息
     *
     * @param ids ids
     * @return Map<Long, Friend>
     */
    public Map<Long, Friend> batchGet(List<Long> ids) {
        List<Friend> list = this.batchGetByIds(ids);
        if (CollectionUtils.isNotEmpty(list)) {
            return list.stream().collect(Collectors.toMap(Friend::getId, Function.identity()));
        }
        return new HashMap<>(16);
    }

    public Friend getFriend(Long accountId, Long friendId) {
        return this.repository.getFriend(accountId, friendId);
    }

    public boolean check(Long accountId, Long friendId) {
        Friend friend = this.repository.getFriend(accountId, friendId);
        return ObjectUtils.isNotNull(friend);
    }

    /**
     * 根据账号id查询关注的人
     *
     * @param accountId accountId
     * @return List<Friend>
     */
    public List<Friend> getFollowers(Long accountId) {
        return this.repository.getByAccountId(accountId);
    }

    /**
     * 根据账号id查询粉丝
     *
     * @param accountId accountId
     * @return List<Friend>
     */
    public List<Friend> getFollowing(Long accountId) {
        return this.repository.getByFriendId(accountId);
    }

    public List<Friend> getFriends(Long accountId) {
        return this.repository.getFriends(accountId);
    }

    /**
     * 批量查询是否关注
     *
     * @param accountId accountId
     * @param friendIds friendIds
     * @return Set<Long>
     */
    public Set<Long> followers(Long accountId, List<Long> friendIds) {
        if (accountId == null || CollectionUtils.isEmpty(friendIds)) {
            return new HashSet<>();
        }
        LambdaQueryWrapper<Friend> wrapper = new LambdaQueryWrapper<>();
        wrapper.eq(Friend::getAccountId, accountId)
                .in(Friend::getFriendId, friendIds);
        List<Friend> likes = this.repository.getFriendList(accountId, friendIds);
        return likes.stream().map(Friend::getFriendId).collect(Collectors.toSet());
    }

    public PageResult<Friend> pageGetFollowing(Long accountId, int pageNum, int pageSize) {
        return this.repository.pageGetFollowing(accountId, pageNum, pageSize);
    }
}
