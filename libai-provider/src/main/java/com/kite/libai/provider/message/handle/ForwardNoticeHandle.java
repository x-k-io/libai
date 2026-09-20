package com.kite.libai.provider.message.handle;

import com.kite.libai.provider.account.model.entity.Account;
import com.kite.libai.provider.account.service.AccountService;
import com.kite.libai.provider.community.model.entity.Article;
import com.kite.libai.provider.community.model.entity.Friend;
import com.kite.libai.provider.community.service.ArticleService;
import com.kite.libai.provider.community.service.FriendService;
import com.kite.libai.provider.message.model.dto.ForwardNotice;
import com.kite.libai.provider.message.model.entity.Notice;
import com.kite.libai.provider.message.model.response.NoticeResponse;
import org.springframework.stereotype.Component;

import com.kite.libai.common.context.RequestContextUtils;
import com.kite.libai.common.utils.JsonUtils;
import com.kite.libai.provider.message.enums.NoticeType;
import com.kite.libai.provider.message.enums.RelationType;
import com.kite.libai.provider.message.model.response.notice.ForwardNoticeResponse;

import lombok.AllArgsConstructor;

@Component
@AllArgsConstructor
public class ForwardNoticeHandle implements NoticeHandle {

    private final AccountService accountService;

    private final ArticleService articleService;

    private final FriendService friendService;

    @Override
    public String getType() {
        return NoticeType.FORWARD.getType();
    }

    @Override
    public NoticeResponse handle(Notice notice) {
        NoticeResponse noticeResponse = new NoticeResponse();
        noticeResponse.setId(notice.getId());
        noticeResponse.setType(notice.getType());
        noticeResponse.setReceiveId(notice.getReceiveId());
        ForwardNotice forwardNotice = JsonUtils.parse(notice.getContent(), ForwardNotice.class);
        ForwardNoticeResponse response = new ForwardNoticeResponse();
        Account account = accountService.getById(forwardNotice.getAccountId());
        response.setAccountId(forwardNotice.getAccountId());
        if (account != null) {
            response.setNickname(account.getNickname());
            response.setAvatar(account.getAvatar());
        }
        Article article = articleService.getById(forwardNotice.getArticleId());
        if (article != null) {
            response.setTitle(article.getTitle());
            response.setCover(article.getCover());
        }
        Friend friend = friendService.getFriend(forwardNotice.getAccountId(), RequestContextUtils.getAccountId());
        if (friend == null) {
            response.setRelation(RelationType.STRANGER.getType());
        } else {
            if (friend.getFriend()) {
                response.setRelation(RelationType.FRIEND.getType());
            } else {
                response.setRelation(RelationType.FOLLOWER.getType());
            }
        }
        response.setArticleId(forwardNotice.getArticleId());
        noticeResponse.setDetail(response);
        noticeResponse.setCreatedAt(notice.getCreatedAt());
        return noticeResponse;
    }
}
