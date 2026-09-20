package com.kite.libai.provider.community.service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.function.Function;
import java.util.stream.Collectors;

import com.baomidou.mybatisplus.core.toolkit.ObjectUtils;
import com.kite.libai.core.repository.BaseService;
import com.kite.libai.provider.community.model.entity.Blacklist;
import com.kite.libai.provider.community.repository.BlacklistRepository;
import org.springframework.stereotype.Service;

import org.apache.commons.collections4.CollectionUtils;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
public class BlacklistService extends BaseService<BlacklistRepository, Blacklist> {

    /**
     * 批量查询好友信息
     *
     * @param ids ids
     * @return Map<Long, Blacklist>
     */
    public Map<Long, Blacklist> batchGet(List<Long> ids) {
        List<Blacklist> list = this.repository.batchGetByIds(ids);
        if (CollectionUtils.isNotEmpty(list)) {
            return list.stream().collect(Collectors.toMap(Blacklist::getId, Function.identity()));
        }
        return new HashMap<>(16);
    }

    public Blacklist getBlacklist(Long accountId, Long theirId) {
        return this.repository.getBlacklist(accountId, theirId);
    }

    public boolean check(Long accountId, Long theirId) {
        Blacklist blacklist = this.repository.getBlacklist(accountId, theirId);
        return ObjectUtils.isNotNull(blacklist);
    }

    public List<Blacklist> getBlacklistByAccountId(Long accountId) {
        return this.repository.getBlacklistByAccountId(accountId);
    }
}
