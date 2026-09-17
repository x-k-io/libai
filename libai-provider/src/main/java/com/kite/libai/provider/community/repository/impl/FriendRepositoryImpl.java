package com.kite.libai.provider.community.repository.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.kite.libai.boot.repository.MybatisBaseRepository;
import com.kite.libai.core.result.PageResult;
import com.kite.libai.provider.community.mapper.FriendMapper;
import com.kite.libai.provider.community.model.entity.Friend;
import com.kite.libai.provider.community.repository.FriendRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class FriendRepositoryImpl extends MybatisBaseRepository<FriendMapper, Friend> implements FriendRepository {

    @Override
    public Friend getFriend(Long accountId, Long friendId) {
        LambdaQueryWrapper<Friend> wrapper = new LambdaQueryWrapper<>();
        wrapper.eq(Friend::getAccountId, accountId).eq(Friend::getFriendId, friendId);
        return baseMapper.selectOne(wrapper);
    }

    @Override
    public List<Friend> getFriendList(Long accountId, List<Long> friendIds) {
        LambdaQueryWrapper<Friend> wrapper = new LambdaQueryWrapper<>();
        wrapper.eq(Friend::getAccountId, accountId).in(Friend::getFriendId, friendIds);
        return baseMapper.selectList(wrapper);
    }

    @Override
    public List<Friend> getByAccountId(Long accountId) {
        LambdaQueryWrapper<Friend> wrapper = new LambdaQueryWrapper<>();
        wrapper.eq(Friend::getAccountId, accountId).orderByDesc(Friend::getCreatedAt);
        return baseMapper.selectList(wrapper);
    }

    @Override
    public List<Friend> getByFriendId(Long friendId) {
        LambdaQueryWrapper<Friend> wrapper = new LambdaQueryWrapper<>();
        wrapper.eq(Friend::getFriendId, friendId).orderByDesc(Friend::getCreatedAt);
        return baseMapper.selectList(wrapper);
    }

    @Override
    public List<Friend> getFriends(Long accountId) {
        LambdaQueryWrapper<Friend> wrapper = new LambdaQueryWrapper<>();
        wrapper.eq(Friend::getAccountId, accountId)
                .eq(Friend::getFriend, Boolean.TRUE)
                .orderByDesc(Friend::getCreatedAt);
        return baseMapper.selectList(wrapper);
    }

    @Override
    public PageResult<Friend> pageGetFollowing(Long accountId, int pageNum, int pageSize) {
        LambdaQueryWrapper<Friend> wrapper = new LambdaQueryWrapper<>();
        wrapper.eq(Friend::getFriend, accountId).orderByDesc(Friend::getCreatedAt);
        return this.pageGet(wrapper, pageNum, pageSize);
    }
}
