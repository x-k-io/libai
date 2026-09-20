package com.kite.libai.provider.community.repository.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.kite.libai.core.repository.MybatisBaseRepository;

import org.apache.commons.lang3.StringUtils;
import com.kite.libai.provider.community.mapper.TopicMapper;
import com.kite.libai.provider.community.model.entity.Topic;
import com.kite.libai.provider.community.repository.TopicRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class TopicRepositoryImpl extends MybatisBaseRepository<TopicMapper, Topic> implements TopicRepository {

    @Override
    public Topic getByName(String name) {
        LambdaQueryWrapper<Topic> wrapper = new LambdaQueryWrapper<>();
        wrapper.eq(Topic::getName, name);
        return this.baseMapper.selectOne(wrapper);
    }

    @Override
    public List<Topic> searchByName(String name) {
        LambdaQueryWrapper<Topic> wrapper = new LambdaQueryWrapper<>();
        if (StringUtils.isNotBlank(name)) {
            wrapper.like(Topic::getName, name);
        }
        wrapper.orderByDesc(Topic::getOrders);
        return this.baseMapper.selectList(wrapper);
    }

    @Override
    public List<Topic> getByCategory(String category) {
        LambdaQueryWrapper<Topic> wrapper = new LambdaQueryWrapper<>();
        if (StringUtils.isNotBlank(category)) {
            wrapper.eq(Topic::getCategory, category);
        }
        wrapper.orderByAsc(Topic::getOrders);
        return this.baseMapper.selectList(wrapper);
    }
}
