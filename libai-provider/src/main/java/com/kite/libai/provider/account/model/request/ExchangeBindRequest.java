package com.kite.libai.provider.account.model.request;

import java.io.Serializable;

import jakarta.validation.constraints.NotBlank;

import lombok.Data;

@Data
public class ExchangeBindRequest implements Serializable {
    private static final long serialVersionUID = 1933368636932422141L;

    @NotBlank
    private String appCode;

    @NotBlank
    private String exchangeSt;

    @NotBlank
    private Boolean sure;
}
