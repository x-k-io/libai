package com.kite.libai.provider.common.model;

import lombok.Getter;
import lombok.Setter;

import java.util.concurrent.atomic.AtomicBoolean;

@Getter
public class DoubleBuffer {
    @Setter
    private volatile IdSegment current;
    private volatile IdSegment next;
    private final AtomicBoolean isLoadingNext = new AtomicBoolean(false);
    private volatile boolean nextReady = false;

    public void setNext(IdSegment segment) {
        this.next = segment;
        this.nextReady = true;
    }

    /**
     * 切换到下一个号段
     */
    public void switchToNext() {
        this.current = this.next;
        this.next = null;
        this.nextReady = false;
        this.isLoadingNext.set(false);
    }
}
