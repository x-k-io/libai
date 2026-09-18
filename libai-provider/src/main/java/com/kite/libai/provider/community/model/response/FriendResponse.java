package com.kite.libai.provider.community.model.response;

import java.io.Serializable;
import java.time.LocalDateTime;

import lombok.Data;

@Data
public class FriendResponse implements Serializable {
        private Long id;

    private Long accountId;

    private Long friendId;

    private Boolean friend;

    private LocalDateTime createdAt;

    private String nickname;

    private String avatar;

    private String initials;

    private int follows;

}
