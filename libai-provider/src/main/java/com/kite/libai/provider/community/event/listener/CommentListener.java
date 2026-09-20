package com.kite.libai.provider.community.event.listener;

import com.kite.libai.provider.community.event.model.CommentEvent;
import com.kite.libai.provider.community.model.entity.Comment;
import com.kite.libai.provider.community.model.entity.Entry;
import com.kite.libai.provider.community.service.EntryService;
import org.springframework.stereotype.Component;

import com.google.common.eventbus.Subscribe;
import com.kite.libai.common.utils.JsonUtils;
import com.kite.libai.provider.community.enums.CommentEventType;
import com.kite.libai.provider.message.enums.NoticeType;
import com.kite.libai.provider.message.model.dto.CommentNotice;
import com.kite.libai.provider.message.model.request.NoticeRequest;
import com.kite.libai.provider.message.presenter.NoticePresenter;

import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Component
@AllArgsConstructor
public class CommentListener {


    private final EntryService entryService;

    private final NoticePresenter noticePresenter;

    @Subscribe
    public void process(CommentEvent commentEvent) {
        if (commentEvent == null) {
            return;
        }
        Comment comment = commentEvent.getComment();
        if (comment == null) {
            return;
        }
        Entry entry = entryService.getById(comment.getEntryId());
        if (entry == null) {
            return;
        }
        if (CommentEventType.CREATED.getType().equals(commentEvent.getType())) {
            // 评论人不是作者本身发送通知
            if (!entry.getAuthorId().equals(comment.getAccountId())) {
                // 发送通知
                CommentNotice commentNotice = new CommentNotice();
                commentNotice.setAccountId(comment.getAccountId());
                commentNotice.setCommentId(comment.getId());
                commentNotice.setArticleId(comment.getEntryId());
                commentNotice.setContent(comment.getContent());
                NoticeRequest noticeRequest = new NoticeRequest();
                noticeRequest.setReceiveId(entry.getAuthorId());
                noticeRequest.setType(NoticeType.REPLY.getType());
                noticeRequest.setContent(JsonUtils.toJson(commentNotice));
                noticePresenter.create(noticeRequest);
            }
        } else {
            // 发送评论删除通知
        }
    }
}
