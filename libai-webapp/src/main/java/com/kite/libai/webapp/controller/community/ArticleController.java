package com.kite.libai.webapp.controller.community;

import com.kite.libai.core.context.RequestContextUtils;
import com.kite.libai.core.result.Result;
import com.kite.libai.provider.community.model.request.ArticleRequest;
import com.kite.libai.provider.community.presenter.ArticlePresenter;
import com.kite.libai.provider.community.presenter.EntryPublishPresenter;
import com.kite.libai.provider.community.enums.ArticleType;
import com.kite.libai.provider.community.enums.AuthorType;
import com.kite.libai.security.annotation.KitePermission;

import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

@Slf4j
@Validated
@RestController
@AllArgsConstructor
@RequestMapping(value = "/api/app/v1/articles")
public class ArticleController {

    private final ArticlePresenter articlePresenter;

    private final EntryPublishPresenter entryPublishPresenter;

    @PostMapping
    @KitePermission
    public Result<Boolean> create(@Valid @RequestBody ArticleRequest articleRequest) {
        articleRequest.setAccountId(RequestContextUtils.getAccountId());
        articleRequest.setType(ArticleType.ARTICLE.getType());
        articleRequest.setAuthorType(AuthorType.UGC.getCode());
        entryPublishPresenter.create(articleRequest);
        return Result.success(Boolean.TRUE);
    }

    @PutMapping(value = "/{id}")
    @KitePermission
    public Result<Boolean> update(@PathVariable Long id, @Valid @RequestBody ArticleRequest articleRequest) {
        articleRequest.setAccountId(RequestContextUtils.getAccountId());
        articleRequest.setType(ArticleType.ARTICLE.getType());
        articleRequest.setAuthorType(AuthorType.UGC.getCode());
        boolean res = articlePresenter.updateById(id, articleRequest);
        return Result.success(res);
    }
}
