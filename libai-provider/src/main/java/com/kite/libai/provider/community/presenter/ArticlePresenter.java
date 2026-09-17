package com.kite.libai.provider.community.presenter;

import com.kite.libai.provider.community.assemble.ArticleAssemble;
import com.kite.libai.provider.community.model.request.ArticleRequest;
import com.kite.libai.provider.community.service.AuditService;
import com.kite.libai.provider.community.service.BrowseService;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.stereotype.Component;

import com.kite.libai.provider.account.service.AccountService;
import com.kite.libai.provider.throne.service.UserService;
import com.kite.libai.provider.community.service.ArticleService;
import com.kite.libai.provider.community.service.EntryService;
import com.kite.libai.provider.throne.service.UserRoleService;
import com.kite.libai.boot.properties.KiteLibaiProperties;

import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Component
@AllArgsConstructor
public class ArticlePresenter {

    private final UserService userService;

    private final AuditService auditService;

    private final BrowseService browseService;

    private final AccountService accountService;

    private final ArticleService articleService;

    private final ArticleAssemble articleAssemble;

    private final KiteLibaiProperties kiteFlyProperties;

    private final UserRoleService userRoleService;

    private final ApplicationEventPublisher applicationEventPublisher;

    private final EntryService entryService;

    /**
     * 更新文章
     *
     * @param id             id
     * @param articleRequest articleRequest
     * @return boolean
     */
    public boolean updateById(Long id, ArticleRequest articleRequest) {
        return Boolean.TRUE;
    }

}
