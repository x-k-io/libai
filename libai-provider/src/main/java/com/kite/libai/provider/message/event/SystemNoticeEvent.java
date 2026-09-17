package com.kite.libai.provider.message.event;

import com.kite.libai.provider.message.model.dto.SystemNotice;
import org.springframework.context.ApplicationEvent;

import lombok.Getter;

@Getter
public class SystemNoticeEvent extends ApplicationEvent {

    private Long receiveId;

    private SystemNotice systemNotice;

    public SystemNoticeEvent(Object source, Long receiveId, SystemNotice systemNotice) {
        super(source);
        this.receiveId = receiveId;
        this.systemNotice = systemNotice;
    }
}
