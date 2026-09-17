package com.kite.libai.admin.controller.throne;

import com.kite.libai.core.result.PageResult;
import com.kite.libai.provider.throne.model.request.CreateRoleRequest;
import com.kite.libai.provider.throne.model.request.UpdateRolePermissionRequest;
import com.kite.libai.provider.throne.model.request.UpdateRoleRequest;
import com.kite.libai.provider.throne.model.vo.RoleVo;
import com.kite.libai.provider.throne.presenter.RolePresenter;
import com.kite.libai.security.annotation.KitePermission;
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
@RequestMapping("/api/admin/v1/roles")
public class AdminRoleController {

    private final RolePresenter rolePresenter;

    @KitePermission
    @RequestMapping(value = "/create", method = RequestMethod.POST)
    public boolean create(@RequestBody CreateRoleRequest request) {
        return rolePresenter.create(request);
    }

    @KitePermission
    @RequestMapping(value = "/update", method = RequestMethod.PUT)
    public boolean updateById(@RequestBody UpdateRoleRequest request) {
        return rolePresenter.updateById(request);
    }

    @KitePermission
    @RequestMapping(value = "/{id}", method = RequestMethod.DELETE)
    public boolean deleteById(@PathVariable Long id) {
        return rolePresenter.deleteById(id);
    }

    @KitePermission
    @RequestMapping(value = "/{id}", method = RequestMethod.GET)
    public RoleVo getById(@PathVariable Long id) {
        return rolePresenter.getById(id);
    }

    @KitePermission
    @RequestMapping(value = "/list", method = RequestMethod.GET)
    public List<RoleVo> getRoleList() {
        return rolePresenter.getRoleList();
    }

    @KitePermission
    @RequestMapping(value = "/{id}/permissions", method = RequestMethod.GET)
    public List<Long> getPermissionByRoleId(@PathVariable Long id) {
        return rolePresenter.getPermissionByRoleId(id);
    }

    @KitePermission
    @RequestMapping(value = "/updateRolePermission", method = RequestMethod.PUT)
    public boolean updateRolePermission(@RequestBody UpdateRolePermissionRequest request) {
        return rolePresenter.updateRolePermission(request);
    }

    @KitePermission
    @RequestMapping(value = "/pageGet", method = RequestMethod.GET)
    public PageResult<RoleVo> pageGet(@RequestParam(required = false) String name,
                                      @RequestParam(required = false, defaultValue = "1") Integer pageNum,
                                      @RequestParam(required = false, defaultValue = "20") Integer pageSize) {
        return rolePresenter.pageGet(name, pageNum, pageSize);
    }
}
