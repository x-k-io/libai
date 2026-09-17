package com.kite.libai.provider.account.model.request;

import lombok.Data;

import javax.validation.constraints.NotBlank;
import java.io.Serializable;

@Data
public class UpdatePasswordRequest implements Serializable {

    @NotBlank
    private String oldPassword;

    @NotBlank
    private String newPassword;
}
