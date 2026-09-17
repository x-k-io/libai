package com.kite.libai.provider.throne.model.request;

import lombok.Data;

import java.io.Serializable;

@Data
public class UpdateUserStatusRequest implements Serializable {

    private Long userId;

    private String status;
}
