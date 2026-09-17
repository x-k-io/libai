package com.kite.libai.provider.community.repository;

import com.kite.libai.boot.repository.BaseRepository;
import com.kite.libai.core.result.PageResult;
import com.kite.libai.provider.community.model.entity.Friend;

import java.util.List;


public interface FriendRepository extends BaseRepository<Friend> {

    Friend getFriend(Long accountId, Long friendId);

    List<Friend> getFriendList(Long accountId, List<Long> friendIds);

    List<Friend> getByAccountId(Long accountId);

    List<Friend> getByFriendId(Long friendId);

    List<Friend> getFriends(Long accountId);

    PageResult<Friend> pageGetFollowing(Long accountId, int pageNum, int pageSize);
}
