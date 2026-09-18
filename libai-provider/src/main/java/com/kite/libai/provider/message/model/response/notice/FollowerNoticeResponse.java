package com.kite.libai.provider.message.model.response.notice;

import java.io.Serializable;

import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode(callSuper = true)
public class FollowerNoticeResponse extends BaseNoticeResponse implements Serializable {
        private Long accountId;

    private String nickname;

    private String avatar;

    private String relation;
}
