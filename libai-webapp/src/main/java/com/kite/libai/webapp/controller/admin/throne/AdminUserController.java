package com.kite.libai.webapp.controller.admin.throne;

import com.kite.libai.core.result.PageResult;
import com.kite.libai.provider.throne.model.entity.User;
import com.kite.libai.provider.throne.model.request.CreateUserRequest;
import com.kite.libai.provider.throne.model.request.UpdatePasswordRequest;
import com.kite.libai.provider.throne.model.request.UpdateUserRequest;
import com.kite.libai.provider.throne.model.request.UpdateUserRoleRequest;
import com.kite.libai.provider.throne.model.request.UpdateUserStatusRequest;
import com.kite.libai.provider.throne.model.vo.UserVo;
import com.kite.libai.provider.throne.presenter.UserPresenter;
import com.kite.libai.webapp.annotation.KitePermission;

import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@Slf4j
@RestController
@AllArgsConstructor
@RequestMapping("/api/admin/v1/users")
public class AdminUserController {
    private final UserPresenter userPresenter;

    @KitePermission
    @RequestMapping(value = "/create", method = RequestMethod.POST)
    public boolean create(@RequestBody CreateUserRequest request) {
        return userPresenter.create(request);
    }

    @KitePermission
    @RequestMapping(value = "/update", method = RequestMethod.PUT)
    public boolean updateById(@RequestBody UpdateUserRequest request) {
        return userPresenter.updateById(request);
    }

    @RequestMapping(value = "/{id}/resetPassword", method = RequestMethod.PUT)
    public boolean resetPassword(@PathVariable Long id) {
        return userPresenter.resetPassword(id);
    }

    @KitePermission
    @RequestMapping(value = "/updatePassword", method = RequestMethod.PUT)
    public boolean updatePassword(@RequestBody UpdatePasswordRequest request) {
        return userPresenter.updatePassword(request);
    }


    @KitePermission
    @RequestMapping(value = "/updateStatus", method = RequestMethod.PUT)
    public boolean updateStatus(@RequestBody UpdateUserStatusRequest request) {
        return userPresenter.updateStatus(request);
    }

    @KitePermission
    @RequestMapping(value = "/{id}", method = RequestMethod.GET)
    public User getById(@PathVariable Long id) {
        return userPresenter.getById(id);
    }

    @KitePermission
    @RequestMapping(value = "/{id}/permissions", method = RequestMethod.GET)
    public List<String> getUserPermissions(@PathVariable Long id) {
        return userPresenter.getUserPermissions(id);
    }

    @KitePermission
    @RequestMapping(value = "/{id}/roles", method = RequestMethod.GET)
    public List<Long> getRoleByUserId(@PathVariable Long id) {
        return userPresenter.getRoleByUserId(id);
    }

    @KitePermission
    @RequestMapping(value = "/updateAccountRole", method = RequestMethod.PUT)
    public boolean updateAccountRole(@RequestBody UpdateUserRoleRequest request) {
        return userPresenter.updateAccountRole(request);
    }

    @KitePermission
    @RequestMapping(value = "/pageGet", method = RequestMethod.GET)
    public PageResult<UserVo> pageGet(@RequestParam(required = false) String name,
                                      @RequestParam(required = false, defaultValue = "1") Integer pageNum,
                                      @RequestParam(required = false, defaultValue = "20") Integer pageSize) {
        return userPresenter.pageGet(name, pageNum, pageSize);
    }

}
