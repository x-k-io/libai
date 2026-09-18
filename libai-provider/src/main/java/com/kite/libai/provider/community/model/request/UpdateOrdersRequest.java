package com.kite.libai.provider.community.model.request;

import java.io.Serializable;
import java.util.List;

import lombok.Data;

@Data
public class UpdateOrdersRequest implements Serializable {
        private List<Long> ids;
}
