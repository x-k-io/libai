package com.kite.libai.provider.message.handle;

import com.kite.libai.provider.account.model.entity.Account;
import com.kite.libai.provider.account.service.AccountService;
import com.kite.libai.provider.community.model.entity.Article;
import com.kite.libai.provider.community.model.entity.Friend;
import com.kite.libai.provider.community.service.ArticleService;
import com.kite.libai.provider.community.service.FriendService;
import com.kite.libai.provider.message.model.dto.LikeNotice;
import com.kite.libai.provider.message.model.entity.Notice;
import com.kite.libai.provider.message.model.response.NoticeResponse;
import com.kite.libai.provider.message.model.response.notice.LikeNoticeResponse;
import org.springframework.stereotype.Component;

import com.kite.libai.core.context.RequestContextUtils;
import com.kite.libai.core.utils.JsonUtils;
import com.kite.libai.provider.message.enums.NoticeType;
import com.kite.libai.provider.message.enums.RelationType;

import lombok.AllArgsConstructor;

@Component
@AllArgsConstructor
public class LikeNoticeHandle implements NoticeHandle {

    private final AccountService accountService;

    private final ArticleService articleService;

    private final FriendService friendService;

    @Override
    public String getType() {
        return NoticeType.LIKE.getType();
    }

    @Override
    public NoticeResponse handle(Notice notice) {
        NoticeResponse noticeResponse = new NoticeResponse();
        noticeResponse.setId(notice.getId());
        noticeResponse.setType(notice.getType());
        noticeResponse.setReceiveId(notice.getReceiveId());
        LikeNotice likeNotice = JsonUtils.parse(notice.getContent(), LikeNotice.class);
        LikeNoticeResponse response = new LikeNoticeResponse();
        Account account = accountService.getById(likeNotice.getAccountId());
        response.setAccountId(likeNotice.getAccountId());
        if (account != null) {
            response.setNickname(account.getNickname());
            response.setAvatar(account.getAvatar());
        }
        Article article = articleService.getById(likeNotice.getArticleId());
        if (article != null) {
            response.setTitle(article.getTitle());
            response.setCover(article.getCover());
        }
        Friend friend = friendService.getFriend(likeNotice.getAccountId(), RequestContextUtils.getAccountId());
        if (friend == null) {
            response.setRelation(RelationType.STRANGER.getType());
        } else {
            if (friend.getFriend()) {
                response.setRelation(RelationType.FRIEND.getType());
            } else {
                response.setRelation(RelationType.FOLLOWER.getType());
            }
        }
        response.setArticleId(likeNotice.getArticleId());
        noticeResponse.setDetail(response);
        noticeResponse.setCreatedAt(notice.getCreatedAt());
        return noticeResponse;
    }
}
