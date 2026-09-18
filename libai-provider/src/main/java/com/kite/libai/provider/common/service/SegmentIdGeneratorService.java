package com.kite.libai.provider.common.service;

import com.kite.libai.provider.common.enums.IdBizType;
import com.kite.libai.provider.common.model.DoubleBuffer;
import com.kite.libai.provider.common.model.IdSegment;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Service;

import jakarta.annotation.PreDestroy;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.*;

@Slf4j
@Service
@RequiredArgsConstructor
public class SegmentIdGeneratorService {

    private final IdSegmentService idSegmentService;

    private final ConcurrentHashMap<String, DoubleBuffer> bufferMap = new ConcurrentHashMap<>();

    /**
     * 有界队列 + 丢弃策略：预加载失败不影响正确性，兜底路径会同步加载
     */
    private final ExecutorService asyncLoader = new ThreadPoolExecutor(
            4, 8, 60L, TimeUnit.SECONDS,
            new ArrayBlockingQueue<>(256),
            r -> {
                Thread t = new Thread(r, "id-loader");
                t.setDaemon(true);
                return t;
            },
            new ThreadPoolExecutor.AbortPolicy());


    /**
     * 号段用到该比例时触发异步预加载
     */
    private static final double PRELOAD_THRESHOLD = 0.2;

    public long genUniqueId() {
        return nextId(IdBizType.UNIQUE_ID.getBizType());
    }

    public long genOrderNo() {
        return nextId(IdBizType.ORDER_NO.getBizType());
    }

    public long genUserNo() {
        return nextId(IdBizType.USER_NO.getBizType());
    }

    /**
     * 启动后预热所有业务号段，消除首个请求的冷启动延迟
     */
    @EventListener(ApplicationReadyEvent.class)
    public void warmUp() {
        List<String> failed = new ArrayList<>();
        long start = System.currentTimeMillis();
        for (IdBizType type : IdBizType.values()) {
            try {
                preloadOnly(type.getBizType());
            } catch (Exception e) {
                failed.add(type.getBizType());
            }
        }
        log.info("号段预热完成: 成功 {} 个, 耗时 {}ms",
                IdBizType.values().length - failed.size(), System.currentTimeMillis() - start);
        if (!failed.isEmpty()) {
            log.error("号段预热失败的业务，请检查 id_alloc 是否已初始化: {}", failed);
        }
    }

    private void preloadOnly(String bizType) {
        DoubleBuffer buffer = bufferMap.computeIfAbsent(bizType, k -> new DoubleBuffer());
        if (buffer.getCurrent() == null) {
            synchronized (buffer) {
                if (buffer.getCurrent() == null) {
                    buffer.setCurrent(idSegmentService.load(bizType));
                }
            }
        }
    }

    private long nextId(String bizType) {
        DoubleBuffer buffer = bufferMap.computeIfAbsent(bizType, k -> new DoubleBuffer());

        // 首次初始化，IO 放在 computeIfAbsent 之外
        if (buffer.getCurrent() == null) {
            synchronized (buffer) {
                if (buffer.getCurrent() == null) {
                    buffer.setCurrent(idSegmentService.load(bizType));
                }
            }
        }

        while (true) {
            IdSegment current = buffer.getCurrent();
            long id = current.nextId();

            if (id != -1) {
                maybePreloadNext(bizType, buffer, current);
                return id;
            }

            // 当前号段耗尽，切换到下一段
            synchronized (buffer) {
                if (buffer.getCurrent() != current) {
                    continue;   // 其他线程已完成切换
                }
                if (!buffer.isNextReady()) {
                    // 预加载没跟上，同步兜底。频繁出现说明 step 偏小或 DB 变慢
                    log.warn("号段预加载未跟上，退化为同步加载: {}", bizType);
                    buffer.setNext(idSegmentService.load(bizType));
                }
                buffer.switchToNext();
            }
        }
    }

    private void maybePreloadNext(String bizType, DoubleBuffer buffer, IdSegment current) {
        if (current.usagePercent() >= PRELOAD_THRESHOLD
                && !buffer.isNextReady()
                && buffer.getIsLoadingNext().compareAndSet(false, true)) {
            try {
                asyncLoader.execute(() -> {
                    boolean loaded = false;
                    try {
                        IdSegment next = idSegmentService.load(bizType);
                        synchronized (buffer) {
                            if (!buffer.isNextReady()) {
                                buffer.setNext(next);
                                loaded = true;
                            }
                        }
                    } catch (Exception e) {
                        log.error("号段预加载失败: {}", bizType, e);
                    } finally {
                        // 成功时不复位，等 switchToNext 消费掉 next 后复位
                        if (!loaded) {
                            buffer.getIsLoadingNext().set(false);
                        }
                    }
                });
            } catch (RejectedExecutionException e) {
                buffer.getIsLoadingNext().set(false);   // 关键：拒绝也要复位
                log.warn("号段预加载被拒绝，线程池饱和: {}", bizType);
            }
        }
    }

    @PreDestroy
    public void shutdown() {
        asyncLoader.shutdown();
        try {
            if (!asyncLoader.awaitTermination(3, TimeUnit.SECONDS)) {
                asyncLoader.shutdownNow();
            }
        } catch (InterruptedException e) {
            asyncLoader.shutdownNow();
            Thread.currentThread().interrupt();
        }
    }
}
