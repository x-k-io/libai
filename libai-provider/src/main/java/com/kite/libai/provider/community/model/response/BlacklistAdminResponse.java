package com.kite.libai.provider.community.model.response;

import java.io.Serializable;
import java.time.LocalDateTime;

import lombok.Data;

@Data
public class BlacklistAdminResponse implements Serializable {
        private Long id;

    private Long accountId;

    private Long theirId;

    private LocalDateTime createdAt;

}
