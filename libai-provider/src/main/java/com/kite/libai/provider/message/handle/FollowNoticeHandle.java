package com.kite.libai.provider.message.handle;

import com.kite.libai.provider.account.model.entity.Account;
import com.kite.libai.provider.account.service.AccountService;
import com.kite.libai.provider.community.model.entity.Friend;
import com.kite.libai.provider.community.service.FriendService;
import com.kite.libai.provider.message.model.dto.FollowerNotice;
import com.kite.libai.provider.message.model.entity.Notice;
import com.kite.libai.provider.message.model.response.NoticeResponse;
import org.springframework.stereotype.Component;

import com.kite.libai.core.context.RequestContextUtils;
import com.kite.libai.core.utils.JsonUtils;
import com.kite.libai.provider.message.enums.NoticeType;
import com.kite.libai.provider.message.enums.RelationType;
import com.kite.libai.provider.message.model.response.notice.FollowerNoticeResponse;

import lombok.AllArgsConstructor;

@Component
@AllArgsConstructor
public class FollowNoticeHandle implements NoticeHandle {

    private final AccountService accountService;

    private final FriendService friendService;

    @Override
    public String getType() {
        return NoticeType.FOLLOW.getType();
    }

    @Override
    public NoticeResponse handle(Notice notice) {
        NoticeResponse noticeResponse = new NoticeResponse();
        noticeResponse.setId(notice.getId());
        noticeResponse.setType(notice.getType());
        noticeResponse.setReceiveId(notice.getReceiveId());
        FollowerNotice followerNotice = JsonUtils.parse(notice.getContent(), FollowerNotice.class);
        FollowerNoticeResponse response = new FollowerNoticeResponse();
        Account account = accountService.getById(followerNotice.getAccountId());
        response.setAccountId(followerNotice.getAccountId());
        if (account != null) {
            response.setNickname(account.getNickname());
            response.setAvatar(account.getAvatar());
        }
        Friend friend = friendService.getFriend(followerNotice.getAccountId(), RequestContextUtils.getAccountId());
        if (friend == null) {
            response.setRelation(RelationType.STRANGER.getType());
        } else {
            if (friend.getFriend()) {
                response.setRelation(RelationType.FRIEND.getType());
            } else {
                response.setRelation(RelationType.FOLLOWER.getType());
            }
        }
        noticeResponse.setDetail(response);
        noticeResponse.setCreatedAt(notice.getCreatedAt());
        return noticeResponse;
    }
}
