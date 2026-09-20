package com.kite.libai.provider.account.assemble;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import com.kite.libai.common.utils.BeanUtils;
import com.kite.libai.provider.account.model.entity.Account;
import com.kite.libai.provider.account.model.response.AccountResponse;
import com.kite.libai.provider.community.model.entity.UserSocial;
import com.kite.libai.provider.community.service.UserSocialService;
import org.springframework.stereotype.Component;

import org.apache.commons.collections4.CollectionUtils;
import com.kite.libai.common.utils.DesensitizationUtils;

import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Component
@AllArgsConstructor
public class AccountAssemble {

    private final UserSocialService userSocialService;

    public AccountResponse toResponse(Account account) {
        if (account == null) {
            return null;
        }
        AccountResponse response = BeanUtils.copy(account, AccountResponse.class);
        UserSocial userSocial = userSocialService.getByAccountId(account.getId());
        setSocialFields(response, userSocial);
        return response;
    }

    public List<AccountResponse> toResponse(List<Account> accounts) {
        if (CollectionUtils.isEmpty(accounts)) {
            return new ArrayList<>();
        }
        List<Long> accountIds = accounts.stream().map(Account::getId).collect(Collectors.toList());
        Map<Long, UserSocial> userSocials = userSocialService.batchGet(accountIds);
        return accounts.stream().map(x -> {
            AccountResponse response = BeanUtils.copy(x, AccountResponse.class);
            response.setMobile(DesensitizationUtils.mobileNo(x.getMobile()));
            UserSocial userSocial = userSocials.get(x.getId());
            setSocialFields(response, userSocial);
            return response;
        }).collect(Collectors.toList());
    }

    private void setSocialFields(AccountResponse response, UserSocial userSocial) {
        if (userSocial == null) {
            return;
        }
        response.setFollowers(userSocial.getFollowers());
        response.setFollowing(userSocial.getFollowing());
        response.setEntries(userSocial.getEntries());
        response.setReplies(userSocial.getReplies());
        response.setLikes(userSocial.getLikes());
        response.setBrowses(userSocial.getBrowses());
        response.setPlays(userSocial.getPlays());
    }
}
