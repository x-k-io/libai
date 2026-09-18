package com.kite.libai.provider.community.model.request;

import java.io.Serializable;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

@Data
@Schema(name = "FavoriteRequest", description = "收藏")
public class FavoriteRequest implements Serializable {
    private static final long serialVersionUID = 1L;

    @Schema(description = "作品id")
    private Long entryId;

}
