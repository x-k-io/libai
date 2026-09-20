package com.kite.libai.provider.community.presenter;

import com.kite.libai.provider.community.assemble.ArticleAssemble;
import com.kite.libai.provider.community.event.producer.EntryAuditEventProducer;
import com.kite.libai.provider.community.model.entity.Article;
import com.kite.libai.provider.community.model.entity.Entry;
import com.kite.libai.provider.community.model.request.ArticleRequest;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import com.kite.libai.common.exception.ServiceException;
import com.kite.libai.provider.community.enums.EntityType;
import com.kite.libai.provider.account.service.AccountService;
import com.kite.libai.provider.community.service.ArticleService;
import com.kite.libai.provider.community.service.EntryService;

import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Component
@AllArgsConstructor
public class EntryPublishPresenter {

    private final EntryService entryService;


    private final AccountService accountService;

    private final ArticleService articleService;

    private final ArticleAssemble articleAssemble;

    private final EntryAuditEventProducer entryAuditEventProducer;

    /**
     * 发布动态
     *
     * @param request request
     */
    @Transactional(rollbackFor = Exception.class)
    public void create(ArticleRequest request) {
        // 校验作者信息
        accountService.checkAuthor(request.getAccountId());
        // 创建动态
        Article article = articleAssemble.buildArticle(request);
        boolean temp = articleService.save(article);
        if (!temp) {
            throw new ServiceException("创建动态失败");
        }
        // 创建作品信息
        Entry entry = articleAssemble.buildEntry(request);
        entry.setEntityType(EntityType.ARTICLE.getType());
        entry.setEntityId(article.getId());
        boolean result = entryService.createEntry(entry);
        if (!result) {
            throw new ServiceException("创建动态失败");
        }
        // 创建成功发布待审核事件
        entryAuditEventProducer.sendWaitAuditEvent(entry);
    }
}
