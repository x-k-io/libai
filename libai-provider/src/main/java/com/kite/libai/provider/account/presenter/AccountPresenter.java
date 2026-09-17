package com.kite.libai.provider.account.presenter;

import com.kite.libai.boot.cache.CacheKeys;
import com.kite.libai.boot.cache.LibaiRedisTemplate;
import com.kite.libai.core.context.RequestContextUtils;
import com.kite.libai.core.utils.BeanUtils;
import com.kite.libai.provider.account.enums.AccountExceptionCode;
import com.kite.libai.core.utils.RandomUtils;
import com.kite.libai.provider.account.assemble.AccountAssemble;
import com.kite.libai.provider.account.model.entity.Account;
import com.kite.libai.provider.account.model.request.BindMobileRequest;
import com.kite.libai.provider.account.model.request.CreateAccountRequest;
import com.kite.libai.provider.account.model.request.GenUpdateMobileTicketRequest;
import com.kite.libai.provider.account.model.request.UpdateAccountRequest;
import com.kite.libai.provider.account.model.request.ResetPasswordRequest;
import com.kite.libai.provider.account.model.request.UpdatePasswordRequest;
import com.kite.libai.provider.account.model.response.AccountResponse;
import com.kite.libai.provider.account.model.response.TheirAccountResponse;
import com.kite.libai.provider.account.service.AccountService;
import com.kite.libai.provider.account.enums.VerifyCodeType;
import com.kite.libai.provider.account.service.VerifyCodeService;
import com.kite.libai.provider.community.service.BlacklistService;
import com.kite.libai.provider.community.service.FriendService;
import com.kite.libai.provider.message.model.dto.SystemNotice;

import org.apache.commons.lang3.StringUtils;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.stereotype.Component;

import com.kite.libai.core.exception.ServiceException;
import com.kite.libai.provider.account.constant.KiteMomentConstant;
import com.kite.libai.provider.account.constant.SystemNoticeTemplate;
import com.kite.libai.provider.account.enums.AccountTypes;
import com.kite.libai.provider.message.event.SystemNoticeEvent;

import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Component
@AllArgsConstructor
public class AccountPresenter {

    private final LibaiRedisTemplate libaiRedisTemplate;

    private final VerifyCodeService verifyCodeService;

    private final FriendService friendService;

    private final AccountService accountService;

    private final AccountAssemble accountAssemble;

    private final BlacklistService blacklistService;

    private final ApplicationEventPublisher applicationEventPublisher;

    /**
     * 创建用户信息
     *
     * @param request request
     * @return boolean
     */
    public Account create(CreateAccountRequest request) {
        request.setType(AccountTypes.ROBOT.getType());
        return accountService.create(request);
    }

    public boolean update(Long id, UpdateAccountRequest request) {
        return accountService.update(id, request);
    }

    public boolean resetPassword(ResetPasswordRequest request) {
        Account account = accountService.getByMobile(request.getMobile());
        if (account == null) {
            throw new ServiceException(AccountExceptionCode.KITE_USER_NO_EXIT);
        }
        verifyCodeService.checkCode(VerifyCodeType.MODIFY_PASSWORD, request.getMobile(), request.getCode());
        return accountService.updatePassword(account.getId(), request.getNewPassword());
    }

    public boolean updateStatus(Long id, String status) {
        return accountService.updateStatus(id, status);
    }

    /**
     * 清空用户头像
     *
     * @param id 账号id
     * @return boolean
     */
    public boolean cleanAvatar(Long id) {
        UpdateAccountRequest request = new UpdateAccountRequest();
        request.setAvatar(KiteMomentConstant.ACCOUNT_AVATAR_DEFAULT);
        boolean result = accountService.update(id, request);
        if (result) {
            SystemNotice systemNotice = new SystemNotice();
            systemNotice.setTitle(SystemNoticeTemplate.CLEAN_AVATAR_TITLE);
            systemNotice.setDetail(SystemNoticeTemplate.CLEAN_AVATAR_DETAIL);
            SystemNoticeEvent event = new SystemNoticeEvent(this, id, systemNotice);
            applicationEventPublisher.publishEvent(event);
        }
        return result;
    }

    public AccountResponse getAccountInfo(Long accountId) {
        Account account = accountService.getById(accountId);
        if (account == null) {
            throw new ServiceException(AccountExceptionCode.KITE_USER_NO_EXIT);
        }
        return accountAssemble.toResponse(account);
    }

    public AccountResponse getTheirInfo(Long accountId, Long theirId) {
        AccountResponse response = getAccountInfo(theirId);
        TheirAccountResponse theirAccountResponse = BeanUtils.copy(response, TheirAccountResponse.class);
        boolean follower = friendService.check(accountId, theirId);
        theirAccountResponse.setFollower(follower);
        boolean blacklist = blacklistService.check(accountId, theirId);
        theirAccountResponse.setBlacklist(blacklist);
        return response;
    }

    public Boolean updatePassword(UpdatePasswordRequest request) {
        Account account = accountService.getById(RequestContextUtils.getAccountId());
        if (account == null) {
            throw new ServiceException(AccountExceptionCode.KITE_USER_NO_EXIT);
        }
        boolean matches = accountService.matches(request.getOldPassword(), account.getPassword());
        if (!matches) {
            throw new ServiceException(AccountExceptionCode.KITE_OLD_PASSWORD_ERROR);
        }
        return accountService.updatePassword(account.getId(), request.getNewPassword());
    }

    public String genUpdateMobileTicket(GenUpdateMobileTicketRequest request) {
        Account account = accountService.getById(RequestContextUtils.getAccountId());
        if (account == null) {
            throw new ServiceException(AccountExceptionCode.KITE_USER_NO_EXIT);
        }
        verifyCodeService.checkCode(VerifyCodeType.MODIFY_MOBILE, account.getMobile(), request.getCode());
        String ticket = RandomUtils.getUUID();
        libaiRedisTemplate.set(CacheKeys.BIND_MOBILE_TICKET_KEY.get(ticket), account.getMobile());
        return ticket;
    }

    public boolean bindMobile(BindMobileRequest request) {
        Account account = accountService.getById(RequestContextUtils.getAccountId());
        if (account == null) {
            throw new ServiceException(AccountExceptionCode.KITE_USER_NO_EXIT);
        }
        String oldMobile = libaiRedisTemplate.get(CacheKeys.BIND_MOBILE_TICKET_KEY.get(request.getTicket()), String.class);
        if (StringUtils.isBlank(oldMobile) || !oldMobile.equalsIgnoreCase(account.getMobile())) {
            throw new ServiceException(AccountExceptionCode.KITE_BIND_MOBILE_TIMEOUT);
        }
        if (StringUtils.equalsIgnoreCase(account.getMobile(), request.getMobile())) {
            throw new ServiceException(AccountExceptionCode.KITE_BIND_MOBILE_ERROR);
        }
        Account newMobileAccount = accountService.getByMobile(request.getMobile());
        if (newMobileAccount != null) {
            throw new ServiceException(AccountExceptionCode.KITE_BIND_MOBILE_EXIT);
        }
        libaiRedisTemplate.delete(CacheKeys.BIND_MOBILE_TICKET_KEY.get(request.getTicket()).getKey());
        verifyCodeService.checkCode(VerifyCodeType.BIND_MOBILE, request.getMobile(), request.getCode());
        account.setMobile(request.getMobile());
        return accountService.updateById(account);
    }
}
