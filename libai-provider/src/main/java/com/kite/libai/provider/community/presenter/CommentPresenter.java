package com.kite.libai.provider.community.presenter;

import java.util.List;

import com.kite.libai.core.result.PageResult;
import com.kite.libai.provider.account.model.entity.Account;
import com.kite.libai.provider.community.assemble.CommentAssemble;
import com.kite.libai.provider.community.event.producer.CommentEventProducer;
import com.kite.libai.provider.community.model.entity.Comment;
import com.kite.libai.provider.community.model.entity.Entry;
import com.kite.libai.provider.community.model.request.CommentRequest;
import com.kite.libai.provider.community.model.response.CommentResponse;
import com.kite.libai.provider.community.service.AuditService;
import com.kite.libai.provider.tripartite.response.AuditResponse;
import com.kite.libai.provider.community.service.CommentService;
import org.springframework.stereotype.Component;

import com.kite.libai.core.exception.ServiceException;
import com.kite.libai.core.result.SystemCode;
import com.kite.libai.provider.account.enums.AccountStatus;
import com.kite.libai.provider.account.service.AccountService;
import com.kite.libai.provider.community.service.EntryService;

import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Component
@AllArgsConstructor
public class CommentPresenter {

    private final EntryService entryService;

    private final AuditService auditService;

    private final AccountService accountService;

    private final CommentService commentService;

    private final CommentAssemble commentAssemble;

    private final CommentEventProducer commentEventProducer;

    /**
     * 创建评论
     *
     * @param request request
     * @return boolean
     */
    public CommentResponse create(CommentRequest request) {
        check(request);
        // 查询作品信息
        Entry entry = entryService.getById(request.getEntryId());
        if (entry == null) {
            throw new ServiceException("未查询到作品信息！");
        }
        // 创建评论
        Comment comment = new Comment();
        comment.setAccountId(request.getAccountId());
        comment.setEntityType(entry.getEntityType());
        comment.setEntityId(entry.getEntityId());
        comment.setEntryId(entry.getId());
        comment.setLikes(0);
        comment.setReplies(0);
        if (comment.getAccountId().equals(entry.getAuthorId())) {
            comment.setAuthored(Boolean.TRUE);
        }
        // 创建评论
        commentService.save(comment);
        // 发送评论创建事件
        commentEventProducer.sendCreateCommentEvent(comment);
        return commentAssemble.toResponse(comment);
    }

    /**
     * 删除评论
     *
     * @param id id
     * @return boolean
     */
    public boolean deleteById(Long id) {
        Comment comment = commentService.getById(id);
        if (comment == null) {
            return Boolean.TRUE;
        }
        boolean result = commentService.deleteById(id);
        if (result) {
            // 发送评论删除事件
            commentEventProducer.sendDeleteCommentEvent(comment);
        }
        return result;
    }

    /**
     * 用户端端查询作品评论
     *
     * @param entryId  entryId
     * @param pageNum  pageNum
     * @param pageSize pageSize
     * @return Result<List < CommentResponse>>
     */
    public PageResult<CommentResponse> getByEntryId(Long entryId, int pageNum, int pageSize) {
        PageResult<Comment> page = commentService.pageGetCommentByEntryId(entryId, pageNum, pageSize);
        List<CommentResponse> responses = commentAssemble.toResponse(page.getRecords());
        return PageResult.success(responses, page.getPageCount(), page.getTotal());
    }

    private void check(CommentRequest request) {
        if (!request.getAudit()) {
            return;
        }
        Account account = accountService.getById(request.getAccountId());
        if (account == null) {
            throw new ServiceException(SystemCode.REQ_REJECT, "未查询到用户信息");
        }
        if (AccountStatus.FORBIDDEN.getStatus().equals(account.getStatus())) {
            throw new ServiceException(SystemCode.REQ_REJECT, "您正在被禁言中,不能发布评论");
        }
        // 评论审核
        AuditResponse audit = auditService.auditText(request.getContent());
        if (audit.isBlock() || audit.isReview()) {
            throw new ServiceException(SystemCode.REQ_REJECT, "发布内容包含违禁词，请去除后发布！");
        }
    }

}
