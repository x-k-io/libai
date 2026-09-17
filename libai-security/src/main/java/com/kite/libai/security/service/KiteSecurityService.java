package com.kite.libai.security.service;


import com.kite.libai.core.model.KiteAccount;

public interface KiteSecurityService {

    KiteAccount getKiteUser();
    KiteAccount getKiteAccount();
}
