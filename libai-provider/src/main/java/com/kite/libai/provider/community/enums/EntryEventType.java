package com.kite.libai.provider.community.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum EntryEventType {

    /**
     * EntryAuditEventType
     */
    RELEASED("released", "发布作品"),
    WAIT_AUDIT("wait_audit", "待审核"),
    AUDIT_FAIL("audit_fail", "审核未通过"),
    DELETED("deleted", "删除作品");

    private final String type;
    private final String desc;
}
