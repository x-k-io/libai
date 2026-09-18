package com.kite.libai.provider.message.model.request;

import java.io.Serializable;

import lombok.Data;

@Data
public class MessageRequest implements Serializable {
        private String type;

    private String content;

}
