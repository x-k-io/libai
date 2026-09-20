package com.kite.libai.provider.account.service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.function.Function;
import java.util.stream.Collectors;

import com.kite.libai.provider.account.model.entity.TripartiteAccount;
import com.kite.libai.provider.account.repository.TripartiteAccountRepository;
import org.springframework.stereotype.Service;

import org.apache.commons.collections4.CollectionUtils;

import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
@AllArgsConstructor
public class TripartiteAccountService {

    private final TripartiteAccountRepository tripartiteAccountRepository;

    public boolean save(TripartiteAccount tripartiteAccount) {
        return tripartiteAccountRepository.save(tripartiteAccount);
    }

    public boolean updateById(TripartiteAccount tripartiteAccount) {
        return tripartiteAccountRepository.updateById(tripartiteAccount);
    }

    public boolean deleteById(Long id) {
        return tripartiteAccountRepository.deleteById(id);
    }

    /**
     * 批量查询三方绑定信息信息
     *
     * @param ids ids
     * @return Map<Long, TripartiteAccount>
     */
    public Map<Long, TripartiteAccount> batchGet(List<Long> ids) {
        List<TripartiteAccount> list = tripartiteAccountRepository.batchGetByIds(ids);
        if (CollectionUtils.isNotEmpty(list)) {
            return list.stream().collect(Collectors.toMap(TripartiteAccount::getId, Function.identity()));
        }
        return new HashMap<>(16);
    }


    /**
     * 查询绑定信息
     *
     * @param appId     appId
     * @param openId    openId
     * @param unionId   unionId
     * @param accountId accountId
     * @return TripartiteAccount
     */
    public TripartiteAccount getTripartiteAccount(String appId, String openId, String unionId, Long accountId) {
        return this.tripartiteAccountRepository.getTripartiteAccount(appId, openId, unionId, accountId);
    }
}
