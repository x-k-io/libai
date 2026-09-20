package com.kite.libai.webapp.service;

import com.kite.libai.boot.service.KiteSecurityService;
import com.kite.libai.common.model.KiteAccount;
import com.kite.libai.provider.account.service.AccountSecurityService;
import com.kite.libai.provider.throne.service.UserSecurityService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class LibaiSecurityService implements KiteSecurityService {

    private final UserSecurityService userSecurityService;

    private final AccountSecurityService accountSecurityService;

    @Override
    public KiteAccount getKiteUser() {
        return userSecurityService.getKiteUser();
    }

    @Override
    public KiteAccount getKiteAccount() {
        return accountSecurityService.getKiteAccount();
    }
}
