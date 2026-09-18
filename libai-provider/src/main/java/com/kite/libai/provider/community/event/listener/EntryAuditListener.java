package com.kite.libai.provider.community.event.listener;

import com.kite.libai.provider.community.event.model.EntryEvent;
import com.kite.libai.provider.community.model.request.EntryAuditRequest;
import com.kite.libai.provider.community.strategy.entry.EntryProcessor;
import com.kite.libai.provider.community.strategy.factory.EntryProcessorFactory;
import org.springframework.stereotype.Component;

import com.google.common.eventbus.Subscribe;
import com.kite.libai.boot.spring.SpringContextUtils;
import com.kite.libai.boot.event.core.BaseListener;
import com.kite.libai.provider.community.enums.EntryEventType;

import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Component
@AllArgsConstructor
public class EntryAuditListener implements BaseListener {


    @Subscribe
    public void process(EntryEvent event) {
        if (event == null) {
            return;
        }
        // 审核作品
        if (EntryEventType.WAIT_AUDIT.getType().equals(event.getEventType())) {
            audit(event);
        }
        // 作品审核通过
        if (EntryEventType.RELEASED.getType().equals(event.getEventType())) {
            pass(event);
        }
        // 作品审核未通过
        if (EntryEventType.AUDIT_FAIL.getType().equals(event.getEventType())) {
            fail(event);
        }
    }

    private void audit(EntryEvent event) {
        log.info("审核作品 event:{}", event);
        EntryProcessorFactory entryProcessorFactory = SpringContextUtils.getBean(EntryProcessorFactory.class);
        if (entryProcessorFactory == null) {
            log.warn("审核作品 entryProcessorFactory is null event:{}", event);
            return;
        }
        EntryProcessor processor = entryProcessorFactory.getByEntityType(event.getEntityType());
        if (processor == null) {
            log.warn("审核作品 processor is null event:{}", event);
            return;
        }
        EntryAuditRequest request = new EntryAuditRequest();
        request.setAuthorId(event.getAuthorId());
        request.setAuthorType(event.getAuthorType());
        request.setEntityType(event.getEntityType());
        request.setEntityId(event.getEntityId());
        request.setEntryId(event.getEntryId());
        processor.audit(request);
    }

    /**
     * 作品审核通过
     *
     * @param event event
     */
    private void pass(EntryEvent event) {
        log.info("作品审核通过 event:{}", event);
    }

    /**
     * 作品审核未通过
     *
     * @param event event
     */
    private void fail(EntryEvent event) {
        log.info("作品审核未通过 event:{}", event);
    }
}
