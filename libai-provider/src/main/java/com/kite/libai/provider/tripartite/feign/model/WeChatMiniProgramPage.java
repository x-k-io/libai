package com.kite.libai.provider.tripartite.feign.model;

import java.io.Serializable;

import lombok.Data;

@Data
public class WeChatMiniProgramPage implements Serializable {
    private String path;
    private String query;
}
