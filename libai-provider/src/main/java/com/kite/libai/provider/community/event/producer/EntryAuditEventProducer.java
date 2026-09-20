package com.kite.libai.provider.community.event.producer;

import com.kite.libai.provider.community.service.EntryService;
import org.springframework.stereotype.Service;

import com.kite.libai.core.event.core.KiteEventService;
import com.kite.libai.provider.community.enums.EntryEventType;
import com.kite.libai.provider.community.event.model.EntryEvent;
import com.kite.libai.provider.community.model.entity.Entry;
import com.kite.libai.provider.community.model.request.EntryAuditRequest;

import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
@AllArgsConstructor
public class EntryAuditEventProducer {

    private final EntryService entryService;

    private final KiteEventService kiteEventService;

    /**
     * 发送作品待审核事件
     *
     * @param entry entry
     */
    public void sendWaitAuditEvent(Entry entry) {
        EntryEvent event = EntryEvent.build(entry, EntryEventType.WAIT_AUDIT);
        kiteEventService.post(event);
    }

    /**
     * 发送作品审核通过事件
     *
     * @param request request
     */
    public void sendAuditPassEvent(EntryAuditRequest request) {
        Entry entry = entryService.getById(request.getEntryId());
        EntryEvent event = EntryEvent.build(entry, EntryEventType.RELEASED);
        kiteEventService.post(event);
    }

    /**
     * 发送作品审核未通过事件
     *
     * @param request request
     */
    public void sendAuditFailEvent(EntryAuditRequest request) {
        Entry entry = entryService.getById(request.getEntryId());
        EntryEvent event = EntryEvent.build(entry, EntryEventType.AUDIT_FAIL);
        kiteEventService.post(event);
    }
}
