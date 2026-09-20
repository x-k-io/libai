package com.kite.libai.provider.account.assemble;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import com.kite.libai.common.utils.BeanUtils;
import com.kite.libai.provider.account.model.entity.TripartiteApp;
import com.kite.libai.provider.account.model.request.TripartiteAppRequest;
import com.kite.libai.provider.account.model.response.TripartiteAppAdminResponse;
import com.kite.libai.provider.account.model.response.TripartiteAppResponse;
import org.springframework.stereotype.Component;

import org.apache.commons.collections4.CollectionUtils;

import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Component
@AllArgsConstructor
public class TripartiteAppAssemble {

    public TripartiteApp toEntity(TripartiteAppRequest tripartiteAppRequest) {
        if (tripartiteAppRequest == null) {
            return null;
        }
        TripartiteApp tripartiteApp = BeanUtils.copy(tripartiteAppRequest, TripartiteApp.class);
        return tripartiteApp;
    }

    public TripartiteAppResponse toResponse(TripartiteApp tripartiteApp) {
        if (tripartiteApp == null) {
            return null;
        }
        TripartiteAppResponse response = BeanUtils.copy(tripartiteApp, TripartiteAppResponse.class);
        return response;
    }

    public List<TripartiteAppResponse> toResponse(List<TripartiteApp> tripartiteApps) {
        if (CollectionUtils.isEmpty(tripartiteApps)) {
            return new ArrayList<>();
        }
        List<TripartiteAppResponse> responses = tripartiteApps.stream().map(tripartiteApp -> {
            TripartiteAppResponse response = BeanUtils.copy(tripartiteApp, TripartiteAppResponse.class);
            return response;
        }).collect(Collectors.toList());
        return responses;
    }

    public TripartiteAppAdminResponse toAdminResponse(TripartiteApp tripartiteApp) {
        if (tripartiteApp == null) {
            return null;
        }
        TripartiteAppAdminResponse response = BeanUtils.copy(tripartiteApp, TripartiteAppAdminResponse.class);
        return response;
    }

    public List<TripartiteAppAdminResponse> toAdminResponse(List<TripartiteApp> tripartiteApps) {
        if (CollectionUtils.isEmpty(tripartiteApps)) {
            return new ArrayList<>();
        }
        List<TripartiteAppAdminResponse> responses = tripartiteApps.stream().map(tripartiteApp -> {
            TripartiteAppAdminResponse response = BeanUtils.copy(tripartiteApp, TripartiteAppAdminResponse.class);
            return response;
        }).collect(Collectors.toList());
        return responses;
    }
}
