package com.kite.libai.provider.community.service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.function.Function;
import java.util.stream.Collectors;

import com.kite.libai.provider.community.mapper.ArticleMapper;
import com.kite.libai.provider.community.model.entity.Article;
import org.apache.commons.collections4.CollectionUtils;
import org.springframework.stereotype.Service;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.spring.service.impl.ServiceImpl;

import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
@AllArgsConstructor
public class ArticleService extends ServiceImpl<ArticleMapper, Article> {

    /**
     * 批量查询文章
     *
     * @param ids ids
     * @return Map<Long, Article>
     */
    public Map<Long, Article> batchGet(List<Long> ids) {
        if (CollectionUtils.isEmpty(ids)) {
            return new HashMap<>(16);
        }
        LambdaQueryWrapper<Article> wrapper = new LambdaQueryWrapper<>();
        wrapper.in(Article::getId, ids);
        List<Article> list = super.list(wrapper);
        if (CollectionUtils.isNotEmpty(list)) {
            return list.stream().collect(Collectors.toMap(Article::getId, Function.identity()));
        }
        return new HashMap<>(16);
    }
}
