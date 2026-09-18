package com.kite.libai.provider.message.model.response.notice;

import java.io.Serializable;

import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode(callSuper = true)
public class SystemNoticeResponse extends BaseNoticeResponse implements Serializable {
        private String title;

    private String subtitle;

    private String cover;

    private String excerpt;

    private String detail;
}
