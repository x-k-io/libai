package com.kite.libai.boot.service;


import com.kite.libai.common.model.KiteAccount;

public interface KiteSecurityService {

    KiteAccount getKiteUser();
    KiteAccount getKiteAccount();
}
