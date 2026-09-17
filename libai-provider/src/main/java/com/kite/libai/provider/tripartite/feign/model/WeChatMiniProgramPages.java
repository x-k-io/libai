package com.kite.libai.provider.tripartite.feign.model;

import java.io.Serializable;
import java.util.List;

import lombok.Data;

@Data
public class WeChatMiniProgramPages implements Serializable {
    private List<WeChatMiniProgramPage> pages;
}
