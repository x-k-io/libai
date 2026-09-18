package com.kite.libai.provider.system.model.request;

import java.io.Serializable;

import cn.afterturn.easypoi.excel.annotation.Excel;
import lombok.Data;

@Data
public class SensitiveWordRequest implements Serializable {
        @Excel(name = "类型", orderNum = "1")
    private String type;

    @Excel(name = "敏感词", orderNum = "2")
    private String word;
}
