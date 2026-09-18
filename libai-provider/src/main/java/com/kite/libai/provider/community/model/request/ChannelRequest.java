package com.kite.libai.provider.community.model.request;

import java.io.Serializable;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

@Data
@Schema(name = "ChannelRequest", description = "频道")
public class ChannelRequest implements Serializable {
    private static final long serialVersionUID = 1L;

    @Schema(description = "分类")
    private String category;

    @Schema(description = "名称")
    private String name;
}
