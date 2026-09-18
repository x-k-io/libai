package com.kite.libai.provider.community.model.response;

import java.io.Serializable;

import lombok.Data;

@Data
public class CommentAdminResponse implements Serializable {
        private Long id;

    private Long accountId;

    private String entityType;

    private Long entityId;

    private Long entryId;

    private String content;

    private Integer likes;

    private Integer replies;

    private Boolean authored;

    private String nickname;

    private String avatar;

    private Boolean liked;

    private String timeDescription;

}
