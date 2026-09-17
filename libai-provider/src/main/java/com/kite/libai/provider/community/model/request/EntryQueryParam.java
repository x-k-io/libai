package com.kite.libai.provider.community.model.request;

import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.util.List;

@Data
@NoArgsConstructor
public class EntryQueryParam implements Serializable {

    private Long authorId;
    private String city;
    private Long channelId;
    private Long circleId;
    private String orderType;
    private List<String> statusList;
    private Integer pageNum = 1;
    private Integer pageSize = 10;
}
