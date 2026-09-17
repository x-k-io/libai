package com.kite.libai.webapp.service;

import com.kite.libai.core.model.KiteAccount;
import com.kite.libai.provider.account.service.AccountSecurityService;
import com.kite.libai.provider.throne.service.UserSecurityService;
import com.kite.libai.security.service.KiteSecurityService;
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
