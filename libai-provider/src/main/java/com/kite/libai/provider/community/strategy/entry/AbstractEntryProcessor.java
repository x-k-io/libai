package com.kite.libai.provider.community.strategy.entry;

import com.kite.libai.provider.community.service.ArticleService;
import com.kite.libai.provider.community.service.AuditService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.kite.libai.provider.community.enums.EntryStatus;
import com.kite.libai.provider.community.event.producer.EntryAuditEventProducer;
import com.kite.libai.provider.community.model.request.EntryAuditRequest;
import com.kite.libai.provider.tripartite.response.AuditResponse;
import com.kite.libai.provider.community.service.EntryService;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
public abstract class AbstractEntryProcessor implements EntryProcessor {

    @Autowired
    protected EntryService entryService;

    @Autowired
    protected AuditService auditService;

    @Autowired
    protected ArticleService articleService;

    @Autowired
    protected EntryAuditEventProducer entryAuditEventProducer;

    /**
     * 审核结果处理
     *
     * @param request       request
     * @param auditResponse auditResponse
     */
    public void auditHandle(EntryAuditRequest request, AuditResponse auditResponse) {
        if (auditResponse.isPass()) {
            // 更改作品审核状态和发布时间
            entryService.auditPass(request.getEntryId());
            // 发送作品审核通过事件
            entryAuditEventProducer.sendAuditPassEvent(request);
        }
        if (auditResponse.isReview()) {
            // 将作品状态改为疑似
            entryService.updateStatus(request.getEntryId(), EntryStatus.SUSPECTED.getStatus());
            // 发送作品审核未通过事件
            entryAuditEventProducer.sendAuditFailEvent(request);
        }
        if (auditResponse.isBlock()) {
            // 将作品状态改为疑似
            entryService.updateStatus(request.getEntryId(), EntryStatus.FAIL.getStatus());
            // 发送作品审核未通过事件
            entryAuditEventProducer.sendAuditFailEvent(request);
        }
    }


}
