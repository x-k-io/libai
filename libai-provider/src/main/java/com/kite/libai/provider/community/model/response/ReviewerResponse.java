package com.kite.libai.provider.community.model.response;

import java.io.Serializable;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

@Data
@Schema(name = "ReviewerResponse", description = "审核人")
public class ReviewerResponse implements Serializable {

    @Schema(description = "主键")
    private Long id;

    @Schema(description = "姓名")
    private String name;
}
