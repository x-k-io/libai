package com.kite.libai.provider.community.assemble;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import com.kite.libai.common.utils.BeanUtils;
import com.kite.libai.provider.account.model.entity.Account;
import com.kite.libai.provider.community.model.entity.Friend;
import com.kite.libai.provider.community.model.entity.UserSocial;
import com.kite.libai.provider.community.model.response.FriendResponse;
import org.springframework.stereotype.Component;

import org.apache.commons.collections4.CollectionUtils;
import com.kite.libai.provider.account.service.AccountService;
import com.kite.libai.provider.community.service.UserSocialService;

import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Component
@AllArgsConstructor
public class FriendAssemble {

    private final AccountService accountService;

    private final UserSocialService userSocialService;

    public List<FriendResponse> toFollowers(List<Friend> friends) {
        if (CollectionUtils.isEmpty(friends)) {
            return new ArrayList<>();
        }
        List<Long> friendIds = friends.stream().map(Friend::getFriendId).distinct().collect(Collectors.toList());
        Map<Long, Account> accounts = accountService.batchGet(friendIds);
        Map<Long, UserSocial> userSocials = userSocialService.batchGet(friendIds);
        return friends.stream().map(x -> {
            FriendResponse response = BeanUtils.copy(x, FriendResponse.class);
            Account account = accounts.get(x.getFriendId());
            if (account != null) {
                response.setNickname(account.getNickname());
                response.setAvatar(account.getAvatar());
                response.setInitials(account.getInitials());
            }
            UserSocial userSocial = userSocials.get(x.getFriendId());
            if (userSocial != null) {
                response.setFollows(userSocial.getFollowers());
            }
            return response;
        }).collect(Collectors.toList());
    }

    public List<FriendResponse> toFollowing(List<Friend> friends) {
        if (CollectionUtils.isEmpty(friends)) {
            return new ArrayList<>();
        }
        List<Long> accountIds = friends.stream().map(Friend::getAccountId).distinct().collect(Collectors.toList());
        Map<Long, Account> accounts = accountService.batchGet(accountIds);
        Map<Long, UserSocial> userSocials = userSocialService.batchGet(accountIds);
        return friends.stream().map(x -> {
            FriendResponse response = BeanUtils.copy(x, FriendResponse.class);
            Account account = accounts.get(x.getAccountId());
            if (account != null) {
                response.setNickname(account.getNickname());
                response.setAvatar(account.getAvatar());
                response.setInitials(account.getInitials());
            }
            UserSocial userSocial = userSocials.get(x.getFriendId());
            if (userSocial != null) {
                response.setFollows(userSocial.getFollowers());
            }
            return response;
        }).collect(Collectors.toList());
    }
}
