package com.kite.libai.provider.account.model.request;

import java.io.Serializable;

import jakarta.validation.constraints.NotBlank;

import lombok.Data;

@Data
public class ExchangeBindRequest implements Serializable {
    @NotBlank
    private String appCode;

    @NotBlank
    private String exchangeSt;

    @NotBlank
    private Boolean sure;
}
