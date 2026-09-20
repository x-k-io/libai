package com.kite.libai.provider.community.assemble;

import java.util.List;
import java.util.stream.Collectors;

import com.kite.libai.common.utils.BeanUtils;
import com.kite.libai.provider.community.model.common.ArticleSection;
import com.kite.libai.provider.community.model.entity.Article;
import com.kite.libai.provider.community.model.entity.Entry;
import com.kite.libai.provider.community.model.request.ArticleRequest;
import org.springframework.stereotype.Component;

import org.apache.commons.collections4.CollectionUtils;
import com.kite.libai.common.utils.ListUtils;
import com.kite.libai.provider.community.enums.EntityType;

import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Component
@AllArgsConstructor
public class ArticleAssemble {


    public ArticleSection build(Article article) {
        if (article == null) {
            return null;
        }
        return BeanUtils.copy(article, ArticleSection.class);
    }

    public List<ArticleSection> build(List<Article> articles) {
        if (CollectionUtils.isEmpty(articles)) {
            return ListUtils.of();
        }
        return articles.stream().map(this::build).collect(Collectors.toList());
    }

    public Article buildArticle(ArticleRequest request) {
        Article article = new Article();
        article.setAuthorId(request.getAccountId());
        article.setType(request.getType());
        article.setAuthorType(request.getAuthorType());
        article.setTitle(request.getTitle());
        article.setSubtitle(request.getSubtitle());
        article.setCover(request.getCover());
        article.setPictures(request.getPicture());
        article.setExcerpt(request.getExcerpt());
        article.setDetail(request.getDetail());
        article.setVideo(request.getVideo());
        return article;
    }

    public Entry buildEntry(ArticleRequest request) {
        Entry entry = new Entry();
        entry.setAuthorId(request.getAccountId());
        entry.setType(request.getType());
        entry.setAuthorType(request.getAuthorType());
        entry.setEntityType(EntityType.ARTICLE.getType());
        entry.setCircleId(request.getCircleId());
        entry.setPlaceId(request.getPlaceId());
        entry.setChannelId(request.getChannelId());
        entry.setLongitude(request.getLongitude());
        entry.setLatitude(request.getLatitude());
        entry.setCountry(request.getCountry());
        entry.setProvince(request.getProvince());
        entry.setCity(request.getCity());
        entry.setDistrict(request.getDistrict());
        entry.setPlace(request.getPlace());
        return entry;
    }
}
