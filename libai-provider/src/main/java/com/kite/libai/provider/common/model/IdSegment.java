package com.kite.libai.provider.common.model;

import java.util.concurrent.ThreadLocalRandom;
import java.util.concurrent.atomic.AtomicLong;

public class IdSegment {

    private final long start;
    private final long end;
    /** 随机步进上限，1 表示严格 +1 */
    private final int incrementMax;
    private final AtomicLong cursor;

    public IdSegment(long start, long end, int incrementMax) {
        if (incrementMax < 1) {
            throw new IllegalArgumentException("maxStep 必须 >= 1，实际: " + incrementMax);
        }
        this.start = start;
        this.end = end;
        this.incrementMax = incrementMax;
        this.cursor = new AtomicLong(start);
    }

    /** 取下一个 ID，返回 -1 表示号段用完 */
    public long nextId() {
        long id = incrementMax == 1
                ? cursor.incrementAndGet()
                : cursor.addAndGet(ThreadLocalRandom.current().nextInt(1, incrementMax + 1));
        return id <= end ? id : -1;
    }

    public double usagePercent() {
        long total = end - start;
        return total <= 0 ? 1.0 : (double) (cursor.get() - start) / total;
    }
}
