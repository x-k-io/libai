package com.kite.libai.provider.system.model.response;

import java.io.Serializable;
import java.time.LocalDateTime;

import lombok.Data;

@Data
public class SensitiveWordResponse implements Serializable {
        private Long id;

    private String type;

    private String word;

    private Boolean status;

    private LocalDateTime createdAt;

    private String createdBy;

    private LocalDateTime updatedAt;

    private String updatedBy;

}
