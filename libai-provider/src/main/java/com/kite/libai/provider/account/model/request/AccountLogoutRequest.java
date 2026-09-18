package com.kite.libai.provider.account.model.request;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

import java.io.Serializable;


@Data
@Schema
public class AccountLogoutRequest implements Serializable {
    private String refreshToken;
}
