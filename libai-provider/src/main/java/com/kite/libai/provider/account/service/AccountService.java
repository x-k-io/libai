package com.kite.libai.provider.account.service;

import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.function.Function;
import java.util.stream.Collectors;

import com.kite.libai.boot.repository.BaseService;
import com.kite.libai.core.enums.UserType;
import com.kite.libai.core.model.KiteAccount;
import com.kite.libai.core.utils.DesensitizationUtils;
import com.kite.libai.core.utils.Exceptions;
import com.kite.libai.provider.account.enums.AccountExceptionCode;
import com.kite.libai.provider.account.model.entity.Account;
import com.kite.libai.provider.account.model.request.CreateAccountRequest;
import com.kite.libai.provider.account.model.request.UpdateAccountRequest;
import com.kite.libai.provider.account.repository.AccountRepository;
import com.kite.libai.provider.community.service.UserSocialService;
import org.apache.commons.collections4.CollectionUtils;

import org.apache.commons.lang3.StringUtils;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.kite.libai.core.exception.ServiceException;
import com.kite.libai.core.result.SystemCode;
import com.kite.libai.provider.account.enums.AccountStatus;
import com.kite.libai.provider.account.enums.AccountTypes;
import com.kite.libai.provider.account.enums.Gender;
import com.kite.libai.provider.account.utils.PinyinUtils;

import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.transaction.annotation.Transactional;

@Slf4j
@Service
@AllArgsConstructor
public class AccountService {

    private final PasswordEncoder passwordEncoder;

    private final UserSocialService userSocialService;

    private final AccountRepository accountRepository;

    public Account getById(Long accountId) {
        return accountRepository.getById(accountId);
    }

    public boolean updateById(Account account) {
        return accountRepository.updateById(account);
    }

    public List<Account> batchGetByIds(List<Long> ids) {
        return accountRepository.batchGetByIds(ids);
    }
    /**
     * 校验用户是否被禁言
     *
     * @param accountId accountId
     */
    public void checkAuthor(Long accountId) {
        Account account = accountRepository.getById(accountId);
        if (account == null) {
            throw new ServiceException(SystemCode.REQ_REJECT, "未查询到用户信息");
        }
        if (AccountTypes.ROBOT.getType().equals(account.getType())) {
            return;
        }
        if (AccountStatus.FORBIDDEN.getStatus().equals(account.getStatus())) {
            throw new ServiceException(SystemCode.REQ_REJECT, "您正在被禁言中,不能发布内容");
        }
    }

    /**
     * 批量查询用户信息
     *
     * @param ids ids
     * @return Map<Long, Account>
     */
    public Map<Long, Account> batchGet(List<Long> ids) {
        List<Account> list = accountRepository.batchGetByIds(ids);
        if (CollectionUtils.isNotEmpty(list)) {
            return list.stream().collect(Collectors.toMap(Account::getId, Function.identity()));
        }
        return new HashMap<>(16);
    }

    /**
     * 创建账号
     *
     * @param request request
     * @return Account
     */
    @Transactional(rollbackFor = {Exception.class})
    public Account create(CreateAccountRequest request) {
        if (AccountTypes.PERSONAL.getType().equals(request.getType())) {
            Account checkAccount = getByMobile(request.getMobile());
            if (checkAccount != null) {
                throw new ServiceException(AccountExceptionCode.KITE_USER_EXIT);
            }
        }
        Account account = new Account();
        account.setType(request.getType());
        account.setMobile(request.getMobile());
        account.setNickname(request.getNickname());
        account.setInitials(PinyinUtils.getFirstLetter(account.getNickname()));
        account.setAvatar(request.getAvatar());
        account.setGender(Gender.UNKNOWN.getKey());
        account.setIdentified(Boolean.FALSE);
        account.setRegisteredAt(LocalDateTime.now());
        account.setStatus(AccountStatus.NORMAL.getStatus());
        try {
            accountRepository.save(account);
            userSocialService.init(account.getId());
            return account;
        } catch (Exception e) {
            log.error(Exceptions.getStackTraceAsString(e));
            throw new ServiceException(SystemCode.FAILURE);
        }
    }

    /**
     * 更新用户信息
     *
     * @param id      id
     * @param request request
     * @return boolean
     */
    public boolean update(Long id, UpdateAccountRequest request) {
        Account account = accountRepository.getById(id);
        if (account == null) {
            throw new ServiceException(AccountExceptionCode.KITE_USER_NO_EXIT);
        }
        if (StringUtils.isNotBlank(request.getNickname())) {
            account.setNickname(request.getNickname());
            account.setInitials(PinyinUtils.getFirstLetter(request.getNickname()));
        }
        if (StringUtils.isNotBlank(request.getAvatar())) {
            account.setAvatar(request.getAvatar());
        }
        if (StringUtils.isNotBlank(request.getGender())) {
            account.setGender(request.getGender());
        }
        if (StringUtils.isNotBlank(request.getBirthday())) {
            account.setBirthday(request.getBirthday());
        }
        if (StringUtils.isNotBlank(request.getIntroduction())) {
            account.setIntroduction(request.getIntroduction());
        }
        if (StringUtils.isNotBlank(request.getCountry())) {
            account.setCountry(request.getCountry());
        }
        if (StringUtils.isNotBlank(request.getProvince())) {
            account.setProvince(request.getProvince());
        }
        if (StringUtils.isNotBlank(request.getCity())) {
            account.setCity(request.getCity());
        }
        if (StringUtils.isNotBlank(request.getDistrict())) {
            account.setDistrict(request.getDistrict());
        }
        return accountRepository.updateById(account);
    }

    public boolean updatePassword(Long id, String newPassword) {
        return accountRepository.updatePassword(id, passwordEncoder.encode(newPassword));
    }

    public boolean updateStatus(Long id, String status) {
        return accountRepository.updateStatus(id, status);
    }

    /**
     * 根据手机号查询用户信息
     *
     * @param mobile 手机号
     * @return 返回结果
     */
    public Account getByMobile(String mobile) {
        return accountRepository.getAccountByMobile(mobile);
    }

    /**
     * 分页查询正常状态的用户
     *
     * @param pageNum  pageNum
     * @param pageSize pageSize
     * @return List<Account>
     */
    public List<Account> getValidAccounts(int pageNum, int pageSize) {
        return accountRepository.getValidAccounts(pageNum, pageSize);
    }

    public List<Account> getAccountList(String type, String nickname, String mobile) {
        return accountRepository.getAccountList(type, nickname, mobile);
    }

    public boolean matches(String password, String dbPassword) {
        return passwordEncoder.matches(password, dbPassword);
    }
}
