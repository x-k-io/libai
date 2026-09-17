package com.kite.libai.provider.throne.model.request;

import java.io.Serializable;

import lombok.Data;

@Data
public class UpdateUserRequest implements Serializable {

    private Long id;

    private String mobile;

    private String avatar;

    private String email;
}
