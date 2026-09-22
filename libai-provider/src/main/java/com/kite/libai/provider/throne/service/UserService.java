package com.kite.libai.provider.throne.service;

import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.function.Function;
import java.util.stream.Collectors;

import com.kite.libai.common.utils.PasswordUtil;
import com.kite.libai.common.utils.RandomType;
import com.kite.libai.common.utils.RandomUtils;
import com.kite.libai.core.repository.BaseService;
import com.kite.libai.common.context.RequestContextUtils;
import com.kite.libai.common.result.PageResult;
import com.kite.libai.common.utils.BeanUtils;
import com.kite.libai.common.utils.DigestUtils;
import com.kite.libai.provider.throne.model.entity.User;
import com.kite.libai.provider.throne.model.request.CreateUserRequest;
import com.kite.libai.provider.throne.model.request.UpdatePasswordRequest;
import com.kite.libai.provider.throne.model.request.UpdateUserRequest;
import com.kite.libai.provider.throne.repository.UserRepository;
import org.apache.commons.collections4.CollectionUtils;

import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Service;

import com.kite.libai.common.exception.ServiceException;
import com.kite.libai.provider.account.enums.AccountExceptionCode;
import com.kite.libai.provider.account.enums.AccountStatus;

import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
@AllArgsConstructor
public class UserService extends BaseService<UserRepository, User> {

    public PageResult<User> pageGet(String name, Integer pageNum, Integer pageSize) {
        return this.repository.pageGet(name, pageNum, pageSize);
    }

    public boolean create(CreateUserRequest createUserRequest) {
        User checkUser = this.getUserByUsername(createUserRequest.getUsername());
        if (checkUser != null) {
            throw new ServiceException(AccountExceptionCode.KITE_USER_EXIT);
        }
        User user = BeanUtils.copy(createUserRequest, User.class);
        String newPassword = RandomUtils.random(16, RandomType.ALL);
        log.info("create newPassword: {}", newPassword);
        user.setPassword(PasswordUtil.encode(DigestUtils.md5Hex("newPassword")));
        user.setStatus(AccountStatus.NORMAL.getStatus());
        return this.repository.insert(user);
    }

    public boolean update(UpdateUserRequest request) {
        User user = this.getById(request.getId());
        if (user == null) {
            throw new ServiceException(AccountExceptionCode.KITE_USER_NO_EXIT);
        }
        if (StringUtils.isNotBlank(request.getMobile())) {
            user.setMobile(request.getMobile());
        }
        if (StringUtils.isNotBlank(request.getEmail())) {
            user.setEmail(request.getEmail());
        }
        if (StringUtils.isNotBlank(request.getAvatar())) {
            user.setAvatar(request.getAvatar());
        }
        user.setUpdateTime(LocalDateTime.now());
        return this.repository.updateById(user);
    }

    public boolean resetPassword(Long id) {
        User user = this.getById(id);
        if (user == null) {
            throw new ServiceException(AccountExceptionCode.KITE_USER_NO_EXIT);
        }
        String newPassword = RandomUtils.random(16, RandomType.ALL);
        log.info("resetPassword newPassword: {}", newPassword);
        String passwordMD5 = DigestUtils.md5Hex(newPassword);
        user.setPassword(PasswordUtil.encode(passwordMD5));
        user.setUpdateTime(LocalDateTime.now());
        return this.updateById(user);
    }


    public boolean updatePassword(UpdatePasswordRequest request) {
        User user = this.getById(RequestContextUtils.getAccountId());
        if (null == user) {
            throw new ServiceException(AccountExceptionCode.KITE_USER_NO_EXIT);
        }
        if (!PasswordUtil.matches(request.getOldPassword(), user.getPassword())) {
            throw new ServiceException(AccountExceptionCode.KITE_OLD_PASSWORD_ERROR);
        }
        user.setPassword(PasswordUtil.encode(request.getNewPassword()));
        user.setUpdateTime(LocalDateTime.now());
        return this.updateById(user);
    }

    /**
     * 批量查询账号信息
     *
     * @param ids ids
     * @return Map<Long, User>
     */
    public Map<Long, User> batchGet(List<Long> ids) {
        if (CollectionUtils.isEmpty(ids)) {
            return new HashMap<>(16);
        }
        List<User> list = this.batchGetByIds(ids);
        if (CollectionUtils.isNotEmpty(list)) {
            return list.stream().collect(Collectors.toMap(User::getId, Function.identity()));
        }
        return new HashMap<>(16);
    }

    public User getUserByUsername(String username) {
        return this.repository.getUserByUsername(username);
    }

    public boolean updateStatus(Long id, String status) {
        return this.repository.updateStatus(id, status);
    }
}
