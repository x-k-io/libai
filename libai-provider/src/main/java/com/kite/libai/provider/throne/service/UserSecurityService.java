package com.kite.libai.provider.throne.service;

import com.kite.libai.core.context.RequestContextUtils;
import com.kite.libai.core.exception.ServiceException;
import com.kite.libai.core.model.KiteAccount;
import com.kite.libai.provider.account.enums.AccountExceptionCode;
import com.kite.libai.provider.account.enums.AccountStatus;
import com.kite.libai.provider.account.model.request.RefreshAccessTokenRequest;
import com.kite.libai.provider.throne.model.entity.UserLoginLog;
import com.kite.libai.provider.throne.model.entity.UserRefreshToken;
import com.kite.libai.provider.throne.model.request.AdminLoginRequest;
import com.kite.libai.provider.throne.model.entity.User;
import com.kite.libai.provider.throne.model.request.UserLogoutRequest;
import com.kite.libai.provider.throne.model.vo.UserTokenVo;
import com.kite.libai.provider.throne.presenter.UserPresenter;
import com.kite.libai.provider.throne.repository.UserLoginLogRepository;
import com.kite.libai.provider.throne.utils.UserJwtUtil;
import lombok.AllArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

@Service
@AllArgsConstructor
public class UserSecurityService {

    private final UserService userService;

    private final UserPresenter userPresenter;

    private final PasswordEncoder passwordEncoder;

    private final UserRefreshTokenService userTokenService;

    private final UserLoginLogRepository userLoginLogRepository;

    public UserTokenVo adminLogin(AdminLoginRequest adminLoginRequest) {
        User user = userService.getUserByUsername(adminLoginRequest.getLoginName());
        if (user == null) {
            throw new ServiceException(AccountExceptionCode.KITE_USER_NO_EXIT);
        }
        if (AccountStatus.LOCKED.getStatus().equals(user.getStatus())) {
            throw new ServiceException(AccountExceptionCode.KITE_USER_IS_LOCK);
        }
        if (!passwordEncoder.matches(adminLoginRequest.getPassword(), user.getPassword())) {
            throw new ServiceException(AccountExceptionCode.KITE_PASSWORD_ERROR);
        }
        UserRefreshToken userRefreshToken = userTokenService.createRefreshToken(user.getId());
        UserTokenVo userToken = UserJwtUtil.generateToken(userRefreshToken);
        savaLoginLog(user.getId());
        return userToken;
    }

    public UserTokenVo refreshAccessToken(RefreshAccessTokenRequest request) {
        UserRefreshToken userRefreshToken = userTokenService.refreshAccessToken(request.getRefreshToken());
        return UserJwtUtil.generateToken(userRefreshToken);
    }

    /**
     * 退出登录
     *
     * @return Result
     */
    public void logout(UserLogoutRequest request) {
        userTokenService.excludeRefreshToken(request.getRefreshToken());
    }

    public KiteAccount getKiteUser() {
        String token = RequestContextUtils.getToken();
        Long userId = UserJwtUtil.getUserId(token);
        return userPresenter.getKiteAccount(userId);
    }

    private void savaLoginLog(Long userId) {
        UserLoginLog userLoginLog = new UserLoginLog();
        userLoginLog.setUserId(userId);
        userLoginLog.setDeviceId(RequestContextUtils.getDeviceId());
        userLoginLog.setIp(RequestContextUtils.getIp());
        userLoginLog.setUserAgent(RequestContextUtils.getUserAgent());
        userLoginLog.setLoginTime(LocalDateTime.now());
        userLoginLog.setCreateTime(LocalDateTime.now());
        userLoginLogRepository.save(userLoginLog);
    }
}
