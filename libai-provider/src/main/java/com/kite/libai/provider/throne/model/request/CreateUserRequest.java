package com.kite.libai.provider.throne.model.request;

import java.io.Serializable;

import javax.validation.constraints.NotBlank;

import lombok.Data;

@Data
public class CreateUserRequest implements Serializable {

    @NotBlank
    private String username;

    @NotBlank
    private String name;

    @NotBlank
    private String mobile;

    private String avatar;

    @NotBlank
    private String email;
}
