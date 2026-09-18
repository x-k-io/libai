package com.kite.libai.provider.community.model.response;

import java.io.Serializable;

import lombok.Data;

@Data
public class ReplyResponse implements Serializable {
        private Long id;

    private Long accountId;

    private String entityType;

    private Long entityId;

    private Long entryId;

    private Long commentId;

    private Long toAccountId;

    private String content;

    private Integer likes;

    private Boolean authored;

    private String nickname;

    private String avatar;

    private String toNickname;

    private Boolean liked;

    private String timeDescription;

}
