package com.kite.libai.provider.message.model.dto;

import java.io.Serializable;

import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode(callSuper = true)
public class SystemNotice extends BaseNotice implements Serializable {
        private String title;

    private String subtitle;

    private String cover;

    private String excerpt;

    private String detail;

    private String schema;
}
