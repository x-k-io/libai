package com.kite.libai.provider.message.model.dto;

import java.io.Serializable;

import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode(callSuper = true)
public class LikeNotice extends BaseNotice implements Serializable {
        private Long accountId;

    private Long articleId;
}
