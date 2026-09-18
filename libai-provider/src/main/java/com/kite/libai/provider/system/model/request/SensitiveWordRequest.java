package com.kite.libai.provider.system.model.request;

import java.io.Serializable;

import cn.afterturn.easypoi.excel.annotation.Excel;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

@Data
@Schema(name = "SensitiveWordRequest", description = "敏感词")
public class SensitiveWordRequest implements Serializable {
    private static final long serialVersionUID = 1L;

    @Excel(name = "类型", orderNum = "1")
    @Schema(description = "类型")
    private String type;

    @Excel(name = "敏感词", orderNum = "2")
    @Schema(description = "词语")
    private String word;
}
