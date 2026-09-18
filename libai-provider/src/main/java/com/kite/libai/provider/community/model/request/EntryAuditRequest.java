package com.kite.libai.provider.community.model.request;

import java.io.Serializable;

import lombok.Data;

@Data
public class EntryAuditRequest implements Serializable {

    private Long authorId;

    private String authorType;

    private String entityType;

    private Long entityId;

    private Long entryId;
}
