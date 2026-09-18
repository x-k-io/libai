package com.kite.libai.provider.community.model.response;

import java.io.Serializable;
import java.time.LocalDateTime;

import lombok.Data;

@Data
public class FriendAdminResponse implements Serializable {
        private Long id;

    private Long accountId;

    private Long friendId;

    private Boolean friend;

    private LocalDateTime createdAt;

}
