package com.kite.libai.provider.throne.model.request;

import java.io.Serializable;

import javax.validation.constraints.NotBlank;

import lombok.Data;

@Data
public class CreateRoleRequest implements Serializable {

    @NotBlank
    private String code;

    @NotBlank
    private String name;

    private String description;
}
