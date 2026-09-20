package com.kite.libai.provider.message.handle;

import com.kite.libai.provider.account.model.entity.Account;
import com.kite.libai.provider.account.service.AccountService;
import com.kite.libai.provider.community.model.entity.Article;
import com.kite.libai.provider.community.service.ArticleService;
import com.kite.libai.provider.message.model.dto.CommentNotice;
import com.kite.libai.provider.message.model.entity.Notice;
import com.kite.libai.provider.message.model.response.NoticeResponse;
import org.springframework.stereotype.Component;

import com.kite.libai.common.utils.JsonUtils;
import com.kite.libai.provider.message.enums.NoticeType;
import com.kite.libai.provider.message.model.response.notice.CommentNoticeResponse;

import lombok.AllArgsConstructor;

@Component
@AllArgsConstructor
public class ReplyNoticeHandle implements NoticeHandle {

    private final AccountService accountService;

    private final ArticleService articleService;

    @Override
    public String getType() {
        return NoticeType.REPLY.getType();
    }

    @Override
    public NoticeResponse handle(Notice notice) {
        NoticeResponse noticeResponse = new NoticeResponse();
        noticeResponse.setId(notice.getId());
        noticeResponse.setType(notice.getType());
        noticeResponse.setReceiveId(notice.getReceiveId());
        CommentNotice commentNotice = JsonUtils.parse(notice.getContent(), CommentNotice.class);
        CommentNoticeResponse response = new CommentNoticeResponse();
        Account account = accountService.getById(commentNotice.getAccountId());
        response.setAccountId(commentNotice.getAccountId());
        if (account != null) {
            response.setNickname(account.getNickname());
            response.setAvatar(account.getAvatar());
        }
        Article article = articleService.getById(commentNotice.getArticleId());
        if (article != null) {
            response.setTitle(article.getTitle());
            response.setCover(article.getCover());
        }
        response.setCommentId(commentNotice.getCommentId());
        response.setArticleId(commentNotice.getArticleId());
        response.setContent(commentNotice.getContent());
        noticeResponse.setDetail(response);
        noticeResponse.setCreatedAt(notice.getCreatedAt());
        return noticeResponse;
    }
}
