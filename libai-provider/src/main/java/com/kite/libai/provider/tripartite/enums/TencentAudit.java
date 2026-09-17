package com.kite.libai.provider.tripartite.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum TencentAudit {
    /**
     * 建议屏蔽
     */
    BLOCK("Block", "建议屏蔽"),
    /**
     * 建议人工复审
     */
    REVIEW("Review", "建议人工复审"),
    /**
     * 建议通过
     */
    PASS("Pass", "建议通过");

    /**
     * 类型
     */
    private String code;
    /**
     * 描述
     */
    private String desc;
}
