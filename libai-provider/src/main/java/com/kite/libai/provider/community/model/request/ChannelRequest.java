package com.kite.libai.provider.community.model.request;

import java.io.Serializable;

import lombok.Data;

@Data
public class ChannelRequest implements Serializable {
        private String category;

    private String name;
}
