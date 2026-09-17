package com.kite.libai.provider.tripartite.response;

import java.io.Serializable;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class AuditResponse implements Serializable {

    private boolean block;

    private boolean review;

    private boolean pass;
}
