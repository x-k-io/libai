package com.kite.libai.provider.account.model.response;

import java.io.Serializable;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;




@Data
@AllArgsConstructor
@NoArgsConstructor
public class AppOauthResponse implements Serializable {

    private String code;

    private String sessionSt;

    private String bindSt;
}
