package com.kite.libai.provider.community.service;

import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.function.Function;
import java.util.stream.Collectors;

import com.kite.libai.core.repository.BaseService;
import com.kite.libai.common.result.PageResult;
import com.kite.libai.provider.community.model.entity.Entry;
import com.kite.libai.provider.community.model.request.EntryQueryParam;
import com.kite.libai.provider.community.repository.EntryRepository;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

import org.apache.commons.collections4.CollectionUtils;
import com.kite.libai.provider.community.enums.ArticleStatus;
import com.kite.libai.provider.community.enums.DataType;
import com.kite.libai.provider.community.enums.EntryStatus;

import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
@AllArgsConstructor
public class EntryService extends BaseService<EntryRepository, Entry> {

    /**
     * 创建作品
     *
     * @param entry entry
     * @return boolean
     */
    public boolean createEntry(Entry entry) {
        // 初始化默认值
        initEntry(entry);
        return this.insert(entry);
    }

    /**
     * 作品审核通过处理
     *
     * @param id id
     */
    public void auditPass(Long id) {
        this.repository.updateStatus(id, EntryStatus.RELEASED.getStatus());
    }

    /**
     * 批量查询作品
     *
     * @param ids ids
     * @return Map<Long, Entry>
     */
    public Map<Long, Entry> batchGet(List<Long> ids) {
        List<Entry> list = batchGetByIds(ids);
        if (CollectionUtils.isNotEmpty(list)) {
            return list.stream().collect(Collectors.toMap(Entry::getId, Function.identity()));
        }
        return new HashMap<>(16);
    }

    public List<Entry> getEntries(Long accountId, LocalDateTime releasedAt) {
        return this.repository.getEntriesByAccountAndReleasedAt(accountId, releasedAt);
    }

    public List<Entry> batchGet(List<Long> accountIds, LocalDateTime releasedAt) {
        return this.repository.getEntriesByAccountAndReleasedAt(accountIds, releasedAt);
    }

    public List<Entry> batchGetByIdAndStatus(List<Long> entryIds) {
        return this.repository.batchGetByIdAndStatus(entryIds, ArticleStatus.RELEASED.getStatus());
    }

    /**
     * 修改作品状态
     *
     * @param id     id
     * @param status status
     * @return boolean
     */
    public boolean updateStatus(Long id, String status) {
        return this.repository.updateStatus(id, status);
    }

    public PageResult<Entry> pageGetEntries(EntryQueryParam queryParam) {
        return this.repository.pageGetEntries(queryParam);
    }

    private void initEntry(Entry entry) {
        entry.setLikes(0);
        entry.setReplies(0);
        entry.setBrowses(0);
        entry.setFavorites(0);
        entry.setPlays(0);
        entry.setCompositeOrders(0L);
        entry.setHotOrders(0L);
        entry.setNewOrders(System.currentTimeMillis());
        entry.setStatus(EntryStatus.UNDER_REVIEW.getStatus());
    }

    @Async
    public void add(Long entryId, DataType dataType) {
        Entry entry = this.getById(entryId);
        if (entry == null) {
            return;
        }
        switch (dataType) {
            case LIKES:
                entry.setLikes(entry.getLikes() + 1);
                break;
            case REPLIES:
                entry.setReplies(entry.getReplies() + 1);
                break;
            case BROWSES:
                entry.setBrowses(entry.getBrowses() + 1);
                break;
            case FAVORITES:
                entry.setFavorites(entry.getFavorites() + 1);
                break;
            case PLAYS:
                entry.setPlays(entry.getPlays() + 1);
                break;
            default:
        }
        this.updateById(entry);
    }

    @Async
    public void reduce(Long entryId, DataType dataType) {
        Entry entry = this.getById(entryId);
        if (entry == null) {
            return;
        }
        switch (dataType) {
            case LIKES:
                entry.setLikes(entry.getLikes() - 1);
                break;
            case REPLIES:
                entry.setReplies(entry.getReplies() - 1);
                break;
            case BROWSES:
                entry.setBrowses(entry.getBrowses() - 1);
                break;
            case FAVORITES:
                entry.setFavorites(entry.getFavorites() - 1);
                break;
            case PLAYS:
                entry.setPlays(entry.getPlays() - 1);
                break;
            default:
        }
        this.updateById(entry);
    }


}
