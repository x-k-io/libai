package com.kite.libai.provider.community.strategy.entry;

import java.util.List;
import java.util.Map;

import com.kite.libai.provider.community.model.common.BaseSection;
import com.kite.libai.provider.community.model.request.EntryAuditRequest;

public interface EntryProcessor {

    /**
     * 查询实体类型
     *
     * @return entityType
     */
    String getEntityType();

    /**
     * 作品审核
     *
     * @param request request
     */
    void audit(EntryAuditRequest request);

    /**
     * 查询作品对应实体信息
     *
     * @param entityId entityId
     * @return BaseSection
     */
    BaseSection getByEntityId(Long entityId);

    /**
     * 批量查询作品对应实体信息
     *
     * @param entityIds entityIds
     * @return Map<Long, BaseSection>
     */
    Map<Long, BaseSection> batchGet(List<Long> entityIds);
}
