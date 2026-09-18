package com.kite.libai.provider.community.model.request;

import java.io.Serializable;

import lombok.Data;

@Data
public class AuditRequest implements Serializable {
        private boolean pass;

    private String reason;
}
