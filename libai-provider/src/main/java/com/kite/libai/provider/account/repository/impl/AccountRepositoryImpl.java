package com.kite.libai.provider.account.repository.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.conditions.update.LambdaUpdateWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.kite.libai.provider.account.enums.AccountStatus;
import com.kite.libai.boot.repository.MybatisBaseRepository;

import org.apache.commons.lang3.StringUtils;
import com.kite.libai.provider.account.mapper.AccountMapper;
import com.kite.libai.provider.account.model.entity.Account;
import com.kite.libai.provider.account.repository.AccountRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class AccountRepositoryImpl extends MybatisBaseRepository<AccountMapper, Account> implements AccountRepository {

    @Override
    public Account getAccountByMobile(String mobile) {
        LambdaQueryWrapper<Account> wrapper = new LambdaQueryWrapper<>();
        wrapper.eq(Account::getMobile, mobile)
                .eq(Account::getStatus, AccountStatus.NORMAL.getStatus());
        return this.baseMapper.selectOne(wrapper);
    }

    @Override
    public List<Account> getAccountByNickname(String nickname) {
        LambdaQueryWrapper<Account> wrapper = new LambdaQueryWrapper<>();
        wrapper.eq(Account::getNickname, nickname);
        return this.baseMapper.selectList(wrapper);
    }

    @Override
    public List<Account> getValidAccounts(int pageNum, int pageSize) {
        LambdaQueryWrapper<Account> wrapper = new LambdaQueryWrapper<>();
        wrapper.eq(Account::getStatus, AccountStatus.NORMAL.getStatus());
        IPage<Account> page = this.baseMapper.selectPage(new Page<>(pageNum, pageSize), wrapper);
        return page.getRecords();
    }

    @Override
    public boolean updateStatus(Long id, String status) {
        LambdaUpdateWrapper<Account> updateWrapper = new LambdaUpdateWrapper<>();
        updateWrapper.set(Account::getStatus, status);
        updateWrapper.eq(Account::getId, id);
        return this.update(updateWrapper);
    }

    @Override
    public boolean updatePassword(Long id, String newPassword) {
        LambdaUpdateWrapper<Account> updateWrapper = new LambdaUpdateWrapper<>();
        updateWrapper.set(Account::getPassword, newPassword);
        updateWrapper.eq(Account::getId, id);
        return this.update(updateWrapper);
    }

    @Override
    public List<Account> getAccountList(String type, String nickname, String mobile) {
        LambdaQueryWrapper<Account> wrapper = new LambdaQueryWrapper<>();
        if (StringUtils.isNotBlank(type)) {
            wrapper.eq(Account::getType, type);
        }
        if (StringUtils.isNotBlank(mobile)) {
            wrapper.like(Account::getMobile, mobile);
        }
        if (StringUtils.isNotBlank(nickname)) {
            wrapper.like(Account::getNickname, nickname);
        }
        return this.baseMapper.selectList(wrapper);
    }
}
