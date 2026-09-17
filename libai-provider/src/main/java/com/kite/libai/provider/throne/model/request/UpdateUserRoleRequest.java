package com.kite.libai.provider.throne.model.request;

import java.io.Serializable;
import java.util.List;

import lombok.Data;

@Data
public class UpdateUserRoleRequest implements Serializable {

    private Long userId;

    private List<Long> roleIds;
}
