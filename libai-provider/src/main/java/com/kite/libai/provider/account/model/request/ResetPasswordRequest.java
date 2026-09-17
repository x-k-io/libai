package com.kite.libai.provider.account.model.request;

import lombok.Data;

import java.io.Serializable;

@Data
public class ResetPasswordRequest implements Serializable {

    private String mobile;

    private String newPassword;

    private String code;
}
