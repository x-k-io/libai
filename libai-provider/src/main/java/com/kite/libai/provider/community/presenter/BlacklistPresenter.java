package com.kite.libai.provider.community.presenter;

import java.util.List;

import com.kite.libai.provider.community.assemble.BlacklistAssemble;
import com.kite.libai.provider.community.model.entity.Blacklist;
import com.kite.libai.provider.community.model.entity.Friend;
import com.kite.libai.provider.community.model.response.BlacklistResponse;
import com.kite.libai.provider.community.service.FriendService;
import com.kite.libai.provider.community.service.BlacklistService;
import org.springframework.stereotype.Component;

import com.kite.libai.provider.message.service.ChatDetailService;

import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Component
@AllArgsConstructor
public class BlacklistPresenter {

    private final FriendService friendService;

    private final BlacklistService blacklistService;

    private final BlacklistAssemble blacklistAssemble;

    private final ChatDetailService chatDetailService;

    public void blacklist(Long accountId, Long theirId) {
        boolean temp = blacklistService.check(accountId, theirId);
        if (temp) {
            return;
        }
        Friend friend = friendService.getFriend(accountId, theirId);
        if (friend != null) {
            friendService.deleteById(friend.getId());
        }
        Friend heFriend = friendService.getFriend(theirId, accountId);
        if (heFriend != null && heFriend.getFriend()) {
            heFriend.setFriend(Boolean.FALSE);
            friendService.updateById(heFriend);
        }
        chatDetailService.blacklist(accountId, theirId);
        chatDetailService.blacklist(theirId, accountId);
        Blacklist blacklist = new Blacklist();
        blacklist.setAccountId(accountId);
        blacklist.setTheirId(theirId);
        blacklistService.save(blacklist);
    }

    public void cancel(Long accountId, Long theirId) {
        Blacklist blacklist = blacklistService.getBlacklist(accountId, theirId);
        if (blacklist != null) {
            blacklistService.deleteById(blacklist.getId());
        }
    }

    public List<BlacklistResponse> getBlacklist(Long accountId) {
        List<Blacklist> blacklist = blacklistService.getBlacklistByAccountId(accountId);
        return blacklistAssemble.build(blacklist);

    }
}
