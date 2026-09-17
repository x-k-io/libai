package com.kite.libai.provider.throne.model.request;

import java.io.Serializable;

import javax.validation.constraints.NotBlank;

import lombok.Data;

@Data
public class UpdatePasswordRequest implements Serializable {

    @NotBlank
    private String oldPassword;

    @NotBlank
    private String newPassword;
}
