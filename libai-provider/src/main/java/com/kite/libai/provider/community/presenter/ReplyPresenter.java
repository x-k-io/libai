package com.kite.libai.provider.community.presenter;

import java.util.List;

import com.kite.libai.common.result.PageResult;
import com.kite.libai.provider.account.model.entity.Account;
import com.kite.libai.provider.community.assemble.ReplyAssemble;
import com.kite.libai.provider.community.event.producer.ReplyEventProducer;
import com.kite.libai.provider.community.model.entity.Entry;
import com.kite.libai.provider.community.model.entity.Reply;
import com.kite.libai.provider.community.model.request.ReplyRequest;
import com.kite.libai.provider.community.model.response.ReplyResponse;
import com.kite.libai.provider.community.service.AuditService;
import com.kite.libai.provider.tripartite.response.AuditResponse;
import org.springframework.stereotype.Component;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.kite.libai.common.exception.ServiceException;
import com.kite.libai.common.result.SystemCode;
import com.kite.libai.provider.account.enums.AccountStatus;
import com.kite.libai.provider.account.service.AccountService;
import com.kite.libai.provider.community.service.EntryService;
import com.kite.libai.provider.community.service.ReplyService;

import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Component
@AllArgsConstructor
public class ReplyPresenter {

    private final AuditService auditService;

    private final ReplyService replyService;

    private final ReplyAssemble replyAssemble;

    private final AccountService accountService;

    private final EntryService entryService;

    private final ReplyEventProducer replyEventProducer;

    /**
     * 创建回复
     *
     * @param request request
     * @return boolean
     */
    public ReplyResponse create(ReplyRequest request) {
        check(request);
        // 查询作品信息
        Entry entry = entryService.getById(request.getEntryId());
        if (entry == null) {
            throw new ServiceException("未查询到作品信息！");
        }
        // 创建回复
        Reply reply = new Reply();
        reply.setAccountId(request.getAccountId());
        reply.setEntityType(entry.getEntityType());
        reply.setEntityId(entry.getEntityId());
        reply.setEntryId(request.getEntryId());
        reply.setCommentId(request.getCommentId());
        reply.setToAccountId(request.getToAccountId());
        reply.setContent(request.getContent());
        reply.setLikes(0);
        // 是否作者回复
        boolean authored = reply.getAccountId().equals(entry.getAuthorId());
        reply.setAuthored(authored);
        // 创建回复
        replyService.save(reply);
        // 发送回复创建事件
        replyEventProducer.sendCreateReplyEvent(reply);
        return replyAssemble.toResponse(reply);
    }

    /**
     * 删除回复
     *
     * @param id id
     * @return boolean
     */
    public boolean deleteById(Long id) {
        Reply reply = replyService.getById(id);
        if (reply == null) {
            return Boolean.TRUE;
        }
        boolean result = replyService.deleteById(id);
        if (result) {
            // 发送回复删除事件
            replyEventProducer.sendDeleteReplyEvent(reply);
        }
        return result;
    }

    /**
     * 查询评论回复
     *
     * @param commentId commentId
     * @param pageNum   pageNum
     * @param pageSize  pageSize
     * @return Result<List < ReplyResponse>>
     */
    public PageResult<ReplyResponse> getByCommentId(Long commentId, int pageNum, int pageSize) {
        LambdaQueryWrapper<Reply> wrapper = new LambdaQueryWrapper<>();
        wrapper.eq(Reply::getCommentId, commentId);
        wrapper.orderByDesc(Reply::getLikes, Reply::getCreatedAt);
        PageResult<Reply> page = replyService.pageGetByCommentId(commentId, pageNum, pageSize);
        List<ReplyResponse> responses = replyAssemble.toResponse(page.getRecords());
        return PageResult.success(responses, page.getPageCount(), page.getTotal());
    }

    private void check(ReplyRequest request) {
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
        // 回复审核
        AuditResponse audit = auditService.auditText(request.getContent());
        if (audit.isBlock() || audit.isReview()) {
            throw new ServiceException(SystemCode.REQ_REJECT, "发布内容包含违禁词，请去除后发布！");
        }
    }
}
