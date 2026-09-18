package com.kite.libai.provider.account.model.request;

import java.io.Serializable;

import lombok.Data;

@Data
public class UpdateAccountRequest implements Serializable {
        private String nickname;

    private String avatar;

    private String gender;

    private String birthday;

    private String introduction;

    private String country;

    private String province;

    private String city;

    private String district;

}
