package com.kite.libai.provider.account.model.response;

import java.io.Serializable;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;


@AllArgsConstructor
@NoArgsConstructor
@Data
public class ExchangeBindResponse implements Serializable {
    private String code;

    private String exchangeSt;

    private String nickname;


}
