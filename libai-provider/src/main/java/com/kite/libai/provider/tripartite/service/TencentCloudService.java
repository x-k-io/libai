package com.kite.libai.provider.tripartite.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.kite.libai.common.utils.Base64Utils;
import com.kite.libai.common.utils.Exceptions;
import com.kite.libai.provider.tripartite.enums.TencentAudit;
import com.kite.libai.provider.tripartite.response.AuditResponse;
import com.kite.libai.core.properties.TencentCloudProperties;
import com.tencentcloudapi.common.Credential;
import com.tencentcloudapi.common.exception.TencentCloudSDKException;
import com.tencentcloudapi.ims.v20200713.ImsClient;
import com.tencentcloudapi.ims.v20200713.models.ImageModerationRequest;
import com.tencentcloudapi.ims.v20200713.models.ImageModerationResponse;
import com.tencentcloudapi.tms.v20200713.TmsClient;
import com.tencentcloudapi.tms.v20200713.models.TextModerationRequest;
import com.tencentcloudapi.tms.v20200713.models.TextModerationResponse;

import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
@AllArgsConstructor
public class TencentCloudService {

    private TencentCloudProperties tencentCloudProperties;

    public AuditResponse textAudit(List<String> contents) {
        for (String content : contents) {
            AuditResponse auditResponse = textAudit(content);
            if (!auditResponse.isPass()) {
                return auditResponse;
            }
        }
        return AuditResponse.builder().pass(Boolean.TRUE).build();
    }

    public AuditResponse textAudit(String content) {
        AuditResponse auditResponse = AuditResponse.builder().build();
        if (!tencentCloudProperties.isEnabled()) {
            auditResponse.setReview(Boolean.TRUE);
            return auditResponse;
        }
        Credential cred = getCredential();
        TmsClient tmsClient = new TmsClient(cred, "ap-beijing");
        TextModerationRequest request = new TextModerationRequest();
        request.setContent(Base64Utils.encode(content));
        try {
            TextModerationResponse response = tmsClient.TextModeration(request);
            String suggestion = response.getSuggestion();
            if (TencentAudit.BLOCK.getCode().equals(suggestion)) {
                auditResponse.setBlock(Boolean.TRUE);
            } else if (TencentAudit.REVIEW.getCode().equals(suggestion)) {
                auditResponse.setReview(Boolean.TRUE);
            } else {
                auditResponse.setPass(Boolean.TRUE);
            }
        } catch (TencentCloudSDKException e) {
            log.info("调用腾讯云识别异常 e:{}", Exceptions.getStackTraceAsString(e));
            auditResponse.setReview(Boolean.TRUE);
        }
        return auditResponse;
    }

    public AuditResponse imageAudit(List<String> urls) {
        for (String url : urls) {
            AuditResponse auditResponse = imageAudit(url);
            if (!auditResponse.isPass()) {
                return auditResponse;
            }
        }
        return AuditResponse.builder().pass(Boolean.TRUE).build();
    }


    public AuditResponse imageAudit(String url) {
        AuditResponse auditResponse = AuditResponse.builder().build();
        if (!tencentCloudProperties.isEnabled()) {
            auditResponse.setReview(Boolean.TRUE);
            return auditResponse;
        }
        Credential cred = getCredential();
        ImsClient imsClient = new ImsClient(cred, "ap-beijing");
        ImageModerationRequest request = new ImageModerationRequest();
        request.setFileUrl(url);
        try {
            ImageModerationResponse response = imsClient.ImageModeration(request);
            String suggestion = response.getSuggestion();
            if (TencentAudit.BLOCK.getCode().equals(suggestion)) {
                auditResponse.setBlock(Boolean.TRUE);
            } else if (TencentAudit.REVIEW.getCode().equals(suggestion)) {
                auditResponse.setReview(Boolean.TRUE);
            } else {
                auditResponse.setPass(Boolean.TRUE);
            }
        } catch (TencentCloudSDKException e) {
            log.info("调用腾讯云识别异常 e:{}", Exceptions.getStackTraceAsString(e));
            auditResponse.setReview(Boolean.TRUE);
        }
        return auditResponse;
    }

    private Credential getCredential() {
        return new Credential(tencentCloudProperties.getSecretId(), tencentCloudProperties.getSecretKey());
    }
}
