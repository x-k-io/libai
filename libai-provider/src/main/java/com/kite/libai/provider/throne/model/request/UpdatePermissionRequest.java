package com.kite.libai.provider.throne.model.request;

import lombok.Data;

import javax.validation.constraints.NotBlank;
import java.io.Serializable;

@Data
public class UpdatePermissionRequest implements Serializable {
    private Long id;

    @NotBlank
    private String name;

    private Integer orders;

}
