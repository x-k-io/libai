package com.kite.libai.provider.common.service;

import com.kite.libai.provider.common.model.IdSegment;
import com.kite.libai.provider.common.model.entity.IdAlloc;
import com.kite.libai.provider.common.repository.IdAllocRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.util.concurrent.ThreadLocalRandom;

@Slf4j
@Service
@RequiredArgsConstructor
public class IdSegmentService {
    private final IdAllocRepository idAllocRepository;

    private static final int MAX_RETRY = 5;

    /**
     * 从数据库获取一个号段（CAS 乐观锁）
     * <p>
     * NOT_SUPPORTED 挂起外层业务事务，避免 RR 隔离级别下读到旧快照导致 CAS 永远失败，
     * 同时防止业务事务长时间持有 id_alloc 行锁。
     */
    @Transactional(propagation = Propagation.NOT_SUPPORTED)
    public IdSegment load(String bizType) {
        for (int retry = 0; retry < MAX_RETRY; retry++) {
            IdAlloc alloc = idAllocRepository.getByBizType(bizType);
            if (alloc == null) {
                throw new IllegalStateException("biz_type 未初始化: " + bizType);
            }

            long maxId = alloc.getMaxId();
            long newMaxId = maxId + alloc.getStep();

            if (idAllocRepository.update(bizType, newMaxId, alloc.getVersion())) {
                return new IdSegment(maxId, newMaxId, alloc.getIncrementMax());
            }

            log.debug("号段 CAS 冲突，重试 {}/{}: {}", retry + 1, MAX_RETRY, bizType);
            backoff(retry);
        }
        throw new IllegalStateException("取号段失败，重试 " + MAX_RETRY + " 次: " + bizType);
    }

    /**
     * 随机退避，避免多实例同时启动时激烈冲突
     */
    private void backoff(int retry) {
        try {
            Thread.sleep(ThreadLocalRandom.current().nextInt(5, 20) * (retry + 1L));
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
            throw new IllegalStateException("取号段被中断: ", e);
        }
    }
}
