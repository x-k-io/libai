package com.kite.libai.provider.community.assemble;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import com.kite.libai.common.utils.BeanUtils;
import com.kite.libai.provider.account.model.entity.Account;
import com.kite.libai.provider.community.model.entity.Blacklist;
import com.kite.libai.provider.community.model.response.BlacklistResponse;
import org.springframework.stereotype.Component;

import org.apache.commons.collections4.CollectionUtils;
import com.kite.libai.provider.account.service.AccountService;

import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Component
@AllArgsConstructor
public class BlacklistAssemble {

    private final AccountService accountService;

    public List<BlacklistResponse> build(List<Blacklist> friends) {
        if (CollectionUtils.isEmpty(friends)) {
            return new ArrayList<>();
        }
        List<Long> theirIds =
                friends.stream().map(Blacklist::getTheirId).distinct().collect(Collectors.toList());
        Map<Long, Account> accounts = accountService.batchGet(theirIds);
        return friends.stream().map(x -> {
            BlacklistResponse response = BeanUtils.copy(x, BlacklistResponse.class);
            Account account = accounts.get(x.getTheirId());
            if (account != null) {
                response.setNickname(account.getNickname());
                response.setAvatar(account.getAvatar());
            }
            return response;
        }).collect(Collectors.toList());
    }
}
