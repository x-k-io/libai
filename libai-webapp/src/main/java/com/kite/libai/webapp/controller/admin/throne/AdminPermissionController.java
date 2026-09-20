package com.kite.libai.webapp.controller.admin.throne;

import com.kite.libai.common.result.PageResult;
import com.kite.libai.provider.throne.model.request.CreatePermissionRequest;
import com.kite.libai.provider.throne.model.request.UpdatePermissionRequest;
import com.kite.libai.provider.throne.model.vo.PermissionVo;
import com.kite.libai.provider.throne.presenter.PermissionPresenter;
import com.kite.libai.boot.annotation.KitePermission;
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
@RequestMapping("/api/admin/v1/permissions")
public class AdminPermissionController {

    private final PermissionPresenter permissionPresenter;

    @KitePermission
    @RequestMapping(value = "/create", method = RequestMethod.POST)
    public boolean create(@RequestBody CreatePermissionRequest request) {
        return permissionPresenter.create(request);
    }

    @KitePermission
    @RequestMapping(value = "/update", method = RequestMethod.PUT)
    public boolean updateById(@RequestBody UpdatePermissionRequest request) {
        return permissionPresenter.updateById(request);
    }

    @KitePermission
    @RequestMapping(value = "/{id}", method = RequestMethod.DELETE)
    public boolean deleteById(@PathVariable Long id) {
        return permissionPresenter.deleteById(id);
    }

    @KitePermission
    @RequestMapping(value = "/{id}", method = RequestMethod.GET)
    public PermissionVo getById(@PathVariable Long id) {
        return permissionPresenter.getById(id);
    }

    @KitePermission
    @RequestMapping(value = "/list", method = RequestMethod.GET)
    public List<PermissionVo> getPermissionList() {
        return permissionPresenter.getPermissionList();
    }

    @KitePermission
    @RequestMapping(value = "/pageGet", method = RequestMethod.GET)
    public PageResult<PermissionVo> pageGet(@RequestParam(required = false) String name,
                                            @RequestParam(required = false, defaultValue = "1") Integer pageNum,
                                            @RequestParam(required = false, defaultValue = "20") Integer pageSize) {
        return permissionPresenter.pageGet(name, pageNum, pageSize);
    }
}
