package com.kite.libai.provider.throne.model.request;

import lombok.Data;

import java.io.Serializable;


@Data
public class UserLogoutRequest implements Serializable {
    private String refreshToken;
}
