package com.kite.libai.core.result;

import java.io.Serializable;

public interface IResultCode extends Serializable {
    /**
     * 获取 code 码
     *
     * @return code码
     */
    int getCode();

    /**
     * 获取描述
     *
     * @return desc描述
     */
    String getMsg();
}
