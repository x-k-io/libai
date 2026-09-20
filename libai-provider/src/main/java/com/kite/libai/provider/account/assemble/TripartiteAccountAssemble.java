package com.kite.libai.provider.account.assemble;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import com.kite.libai.common.utils.BeanUtils;
import com.kite.libai.provider.account.model.entity.TripartiteAccount;
import com.kite.libai.provider.account.model.request.TripartiteAccountRequest;
import com.kite.libai.provider.account.model.response.TripartiteAccountAdminResponse;
import com.kite.libai.provider.account.model.response.TripartiteAccountResponse;
import org.springframework.stereotype.Component;

import org.apache.commons.collections4.CollectionUtils;

import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Component
@AllArgsConstructor
public class TripartiteAccountAssemble {

    public TripartiteAccount toEntity(TripartiteAccountRequest tripartiteAccountRequest) {
        if (tripartiteAccountRequest == null) {
            return null;
        }
        TripartiteAccount tripartiteAccount = BeanUtils.copy(tripartiteAccountRequest, TripartiteAccount.class);
        return tripartiteAccount;
    }

    public TripartiteAccountResponse toResponse(TripartiteAccount tripartiteAccount) {
        if (tripartiteAccount == null) {
            return null;
        }
        TripartiteAccountResponse response = BeanUtils.copy(tripartiteAccount, TripartiteAccountResponse.class);
        return response;
    }

    public List<TripartiteAccountResponse> toResponse(List<TripartiteAccount> tripartiteAccounts) {
        if (CollectionUtils.isEmpty(tripartiteAccounts)) {
            return new ArrayList<>();
        }
        List<TripartiteAccountResponse> responses = tripartiteAccounts.stream().map(tripartiteAccount -> {
            TripartiteAccountResponse response = BeanUtils.copy(tripartiteAccount, TripartiteAccountResponse.class);
            return response;
        }).collect(Collectors.toList());
        return responses;
    }

    public TripartiteAccountAdminResponse toAdminResponse(TripartiteAccount tripartiteAccount) {
        if (tripartiteAccount == null) {
            return null;
        }
        TripartiteAccountAdminResponse response =
                BeanUtils.copy(tripartiteAccount, TripartiteAccountAdminResponse.class);
        return response;
    }

    public List<TripartiteAccountAdminResponse> toAdminResponse(List<TripartiteAccount> tripartiteAccounts) {
        if (CollectionUtils.isEmpty(tripartiteAccounts)) {
            return new ArrayList<>();
        }
        List<TripartiteAccountAdminResponse> responses = tripartiteAccounts.stream().map(tripartiteAccount -> {
            TripartiteAccountAdminResponse response =
                    BeanUtils.copy(tripartiteAccount, TripartiteAccountAdminResponse.class);
            return response;
        }).collect(Collectors.toList());
        return responses;
    }
}
