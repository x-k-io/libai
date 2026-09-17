package com.kite.libai.provider.system.model.request;

import java.io.Serializable;

import cn.afterturn.easypoi.excel.annotation.Excel;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

@Data
@ApiModel(value = "ProhibitedWordRequest", description = "违禁词")
public class ProhibitedWordRequest implements Serializable {
    private static final long serialVersionUID = 1L;

    @Excel(name = "类型", orderNum = "1")
    @ApiModelProperty(value = "类型")
    private String type;

    @Excel(name = "违禁词", orderNum = "2")
    @ApiModelProperty(value = "违禁词")
    private String word;
}
