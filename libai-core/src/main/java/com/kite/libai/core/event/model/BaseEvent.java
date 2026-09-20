package com.kite.libai.core.event.model;

public interface BaseEvent {
    /**
     * 事件是否为异步 默认异步
     *
     * @return boolean
     */
    default boolean async() {
        return Boolean.TRUE;
    }

    /**
     * 事件校验
     *
     * @return 校验结果，校验不通过不会发送事件
     */
    default boolean check() {
        return Boolean.TRUE;
    }
}
