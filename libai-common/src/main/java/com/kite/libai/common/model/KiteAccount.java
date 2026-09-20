package com.kite.libai.common.model;

import lombok.Data;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Data
public class KiteAccount implements Serializable {

    private Long id;

    private Long tenantId;

    private String type;

    private String nickname;

    private String name;

    private String mobile;

    private String avatar;

    private String email;

    private List<String> roles;

    private List<String> permissions = new ArrayList<>();

    private String status;

    private LocalDateTime registeredAt;

    @Data
    public static class Role {

        private Long id;

        private String code;

        private String name;
    }
}
