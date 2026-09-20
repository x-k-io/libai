package com.kite.libai.provider.community.strategy.entry;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.function.Function;
import java.util.stream.Collectors;

import com.kite.libai.provider.community.assemble.ArticleAssemble;
import org.springframework.stereotype.Service;

import org.apache.commons.collections4.CollectionUtils;
import com.kite.libai.common.utils.ListUtils;
import com.kite.libai.provider.community.enums.EntityType;
import com.kite.libai.provider.community.model.common.ArticleSection;
import com.kite.libai.provider.community.model.common.BaseSection;
import com.kite.libai.provider.community.model.entity.Article;
import com.kite.libai.provider.community.model.request.EntryAuditRequest;
import com.kite.libai.provider.tripartite.response.AuditResponse;

import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
@AllArgsConstructor
public class ArticleProcessor extends AbstractEntryProcessor {

    private final ArticleAssemble articleAssemble;

    @Override
    public String getEntityType() {
        return EntityType.ARTICLE.getType();
    }

    @Override
    public void audit(EntryAuditRequest request) {
        Article article = articleService.getById(request.getEntityId());
        // 文本审核
        List<String> contents = ListUtils.of(article.getTitle(), article.getDetail());
        AuditResponse auditResponse = auditService.auditText(contents);
        if (auditResponse.isPass()) {
            List<String> urls = Arrays.asList(article.getPictures().split(","));
            auditResponse = auditService.auditImage(urls);
        }
        // 审核结果处理
        auditHandle(request, auditResponse);
    }

    @Override
    public BaseSection getByEntityId(Long entityId) {
        Article article = articleService.getById(entityId);
        return articleAssemble.build(article);
    }

    @Override
    public Map<Long, BaseSection> batchGet(List<Long> entityIds) {
        List<Article> articles = articleService.listByIds(entityIds);
        List<ArticleSection> sections = articleAssemble.build(articles);
        if (CollectionUtils.isEmpty(sections)) {
            return new HashMap<>(16);
        }
        return sections.stream().collect(Collectors.toMap(ArticleSection::getId, Function.identity()));
    }
}
