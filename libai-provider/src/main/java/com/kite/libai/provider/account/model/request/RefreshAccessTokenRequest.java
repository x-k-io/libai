package com.kite.libai.provider.account.model.request;

import io.swagger.annotations.ApiModel;
import lombok.Data;

import java.io.Serializable;


@Data
@ApiModel
public class RefreshAccessTokenRequest implements Serializable {
    private String refreshToken;
}
