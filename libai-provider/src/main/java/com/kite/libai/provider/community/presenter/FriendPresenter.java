package com.kite.libai.provider.community.presenter;

import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.kite.libai.common.result.PageResult;
import com.kite.libai.provider.community.assemble.FriendAssemble;
import com.kite.libai.provider.community.event.producer.FollowerEventProducer;
import com.kite.libai.provider.community.model.entity.Blacklist;
import com.kite.libai.provider.community.model.entity.Friend;
import com.kite.libai.provider.community.model.response.FriendResponse;
import com.kite.libai.provider.community.service.FriendService;
import com.kite.libai.provider.message.model.entity.ChatDetail;
import org.springframework.stereotype.Component;

import com.kite.libai.common.exception.ServiceException;
import com.kite.libai.provider.message.enums.ChatDetailStatus;
import com.kite.libai.provider.community.service.BlacklistService;
import com.kite.libai.provider.message.service.ChatDetailService;

import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Component
@AllArgsConstructor
public class FriendPresenter {

    private final FriendService friendService;

    private final FriendAssemble friendAssemble;

    private final BlacklistService blacklistService;

    private final ChatDetailService chatDetailService;

    private final FollowerEventProducer followerEventProducer;

    public void follower(Long accountId, Long friendId) {
        if (accountId.equals(friendId)) {
            throw new ServiceException("你已经很优秀了，可以多关注其他车友呦!");
        }
        boolean temp = blacklistService.check(friendId, accountId);
        if (temp) {
            throw new ServiceException("您已被对方拉黑,不能关注");
        }
        boolean check = friendService.check(accountId, friendId);
        if (check) {
            return;
        }
        Friend meFriend = new Friend();
        meFriend.setAccountId(accountId);
        meFriend.setFriendId(friendId);
        Friend heFriend = friendService.getFriend(friendId, accountId);
        if (heFriend != null) {
            heFriend.setFriend(Boolean.TRUE);
            friendService.updateById(heFriend);
            meFriend.setFriend(Boolean.TRUE);
        }
        // 将黑名单清除
        Blacklist blacklist = blacklistService.getBlacklist(accountId, friendId);
        if (blacklist != null) {
            blacklistService.deleteById(blacklist.getId());
        }
        // 关注
        friendService.save(meFriend);
        // 发送关注事件
        followerEventProducer.sendFollowerEvent(accountId, friendId);
    }

    public void cancelFollower(Long accountId, Long friendId) {
        Friend meFriend = friendService.getFriend(accountId, friendId);
        if (meFriend == null) {
            return;
        }
        friendService.deleteById(meFriend.getId());
        Friend heFriend = friendService.getFriend(friendId, accountId);
        if (heFriend != null) {
            heFriend.setFriend(Boolean.FALSE);
            friendService.updateById(heFriend);
            ChatDetail chatDetail = chatDetailService.getChatDetail(accountId, friendId);
            if (chatDetail != null) {
                chatDetail.setStatus(ChatDetailStatus.INVISIBLE.getStatus());
                chatDetailService.updateById(chatDetail);
            }
        } else {
            chatDetailService.blacklist(accountId, friendId);
            chatDetailService.blacklist(friendId, accountId);
        }
        // 发送取消关注事件
        followerEventProducer.sendCancelFollowerEvent(accountId, friendId);
    }

    /**
     * 根据账号id查询关注的人
     *
     * @param accountId accountId
     * @return Result<List < FriendResponse>>
     */
    public List<FriendResponse> getFollowers(Long accountId) {
        List<Friend> followers = friendService.getFollowers(accountId);
        return friendAssemble.toFollowers(followers);
    }

    /**
     * 根据账号id查询粉丝
     *
     * @param accountId accountId
     * @return Result<List < FriendResponse>>
     */
    public PageResult<FriendResponse> getFollowing(Long accountId, int pageNum, int pageSize) {
        PageResult<Friend> page = friendService.pageGetFollowing(accountId, pageNum, pageSize);
        List<FriendResponse> responses = friendAssemble.toFollowing(page.getRecords());
        return PageResult.success(responses, page.getPageCount(), page.getTotal());
    }

    public Map<String, List<FriendResponse>> getAddressBookList(Long accountId) {
        Map<String, List<FriendResponse>> map = new HashMap<>(16);
        // 好友
        List<Friend> friends = friendService.getFriends(accountId);
        map.put("friends", friendAssemble.toFollowers(friends));
        // 全部关注
        List<Friend> followers = friendService.getFollowers(accountId);
        List<FriendResponse> follows = friendAssemble.toFollowers(followers);
        follows.sort(Comparator.comparing(FriendResponse::getInitials));
        map.put("followers", follows);
        return map;
    }

}
