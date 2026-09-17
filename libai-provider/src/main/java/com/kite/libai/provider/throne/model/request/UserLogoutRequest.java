package com.kite.libai.provider.throne.model.request;

import io.swagger.annotations.ApiModel;
import lombok.Data;

import java.io.Serializable;


@Data
@ApiModel
public class UserLogoutRequest implements Serializable {
    private String refreshToken;
}
