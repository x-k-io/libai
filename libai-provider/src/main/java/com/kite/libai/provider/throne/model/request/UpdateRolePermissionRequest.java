package com.kite.libai.provider.throne.model.request;

import lombok.Data;

import java.io.Serializable;
import java.util.List;

@Data
public class UpdateRolePermissionRequest implements Serializable {

    private Long roleId;

    private List<Long> permissionIds;
}
