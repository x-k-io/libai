package com.kite.libai.provider.community.service;

import java.util.List;

import com.kite.libai.provider.tripartite.response.AuditResponse;
import com.kite.libai.provider.tripartite.service.TencentCloudService;
import com.kite.libai.core.properties.KiteLibaiProperties;

import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Service;

import com.kite.libai.provider.system.cache.ProhibitedWordCache;
import com.kite.libai.provider.system.cache.SensitiveWordCache;

import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
@AllArgsConstructor
public class AuditService {

    private final ProhibitedWordCache prohibitedWordCache;

    private final SensitiveWordCache sensitiveWordCache;

    private final TencentCloudService tencentCloudService;

    private final KiteLibaiProperties kiteFlyProperties;


    /**
     * 批量图片云审核
     *
     * @param urls urls
     * @return
     */
    public AuditResponse auditImage(List<String> urls) {
        if (!kiteFlyProperties.isAuditEnabled()) {
            return AuditResponse.builder().pass(Boolean.TRUE).build();
        }
        return tencentCloudService.imageAudit(urls);
    }

    /**
     * 单个图片云审核
     *
     * @param url url
     * @return
     */
    public AuditResponse auditImage(String url) {
        if (!kiteFlyProperties.isAuditEnabled()) {
            return AuditResponse.builder().pass(Boolean.TRUE).build();
        }
        return tencentCloudService.imageAudit(url);
    }

    /**
     * 批量文本自有和云审核
     *
     * @param contents contents
     * @return
     */
    public AuditResponse auditText(List<String> contents) {
        if (!kiteFlyProperties.isAuditEnabled()) {
            return AuditResponse.builder().pass(Boolean.TRUE).build();
        }
        AuditResponse response = localAudit(contents);
        if (!response.isPass()) {
            return response;
        }
        return tencentCloudService.textAudit(contents);
    }

    /**
     * 单个文本自有和云审核
     *
     * @param content content
     * @return
     */
    public AuditResponse auditText(String content) {
        if (!kiteFlyProperties.isAuditEnabled()) {
            return AuditResponse.builder().pass(Boolean.TRUE).build();
        }
        AuditResponse response = localAudit(content);
        if (!response.isPass()) {
            return response;
        }
        return tencentCloudService.textAudit(content);
    }

    /**
     * 文本批量自有审核
     *
     * @param contents contents
     * @return
     */
    public AuditResponse localAudit(List<String> contents) {
        for (String content : contents) {
            AuditResponse auditResponse = localAudit(content);
            if (!auditResponse.isPass()) {
                return auditResponse;
            }
        }
        return AuditResponse.builder().pass(Boolean.TRUE).build();
    }

    /**
     * 单个文本自有审核
     *
     * @param content content
     * @return
     */
    public AuditResponse localAudit(String content) {
        AuditResponse response = AuditResponse.builder().build();
        if (StringUtils.isBlank(content)) {
            return response;
        }
        List<String> prohibitedWords = prohibitedWordCache.getProhibitedWordList();
        for (String prohibitedWord : prohibitedWords) {
            if (content.contains(prohibitedWord)) {
                response.setBlock(Boolean.TRUE);
                return response;
            }
        }
        List<String> sensitiveWords = sensitiveWordCache.getSensitiveWordList();
        for (String sensitiveWord : sensitiveWords) {
            if (content.contains(sensitiveWord)) {
                response.setReview(Boolean.TRUE);
                return response;
            }
        }
        response.setPass(Boolean.TRUE);
        return response;
    }
}
