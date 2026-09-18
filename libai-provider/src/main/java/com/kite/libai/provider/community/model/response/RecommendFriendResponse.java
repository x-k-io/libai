package com.kite.libai.provider.community.model.response;

import java.io.Serializable;

import lombok.Data;

@Data
public class RecommendFriendResponse implements Serializable {
        private Long accountId;

    private String nickname;

    private String avatar;
}
