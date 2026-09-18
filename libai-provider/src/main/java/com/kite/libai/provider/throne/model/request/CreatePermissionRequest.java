package com.kite.libai.provider.throne.model.request;

import java.io.Serializable;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

import lombok.Data;

@Data
public class CreatePermissionRequest implements Serializable {

    @NotNull
    private Long parentId;

    @NotBlank
    private String name;

    private String permission;

    private Integer orders;
}
