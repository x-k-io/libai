package com.kite.libai.provider.community.model.request;

import java.io.Serializable;

import lombok.Data;

@Data
public class ReplyRequest implements Serializable {
        private Boolean audit;

    private Long accountId;

    private Long entryId;

    private Long commentId;

    private Long toAccountId;

    private String content;
}
