package com.kite.libai.provider.community.service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.function.Function;
import java.util.stream.Collectors;

import com.kite.libai.core.repository.BaseService;
import com.kite.libai.common.exception.ServiceException;
import com.kite.libai.provider.community.model.entity.Topic;
import com.kite.libai.provider.community.repository.TopicRepository;
import org.springframework.stereotype.Service;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import org.apache.commons.collections4.CollectionUtils;

import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
@AllArgsConstructor
public class TopicService extends BaseService<TopicRepository, Topic> {

    public Topic getByName(String name) {
        LambdaQueryWrapper<Topic> wrapper = new LambdaQueryWrapper<>();
        wrapper.eq(Topic::getName, name);
        return this.repository.getByName(name);
    }

    /**
     * 更新排序
     *
     * @param id     id
     * @param orders orders
     * @return boolean
     */
    public void updateOrders(Long id, Long orders) {
        Topic topic = this.getById(id);
        if (topic == null) {
            throw new ServiceException("话题不存在");
        }
        topic.setOrders(orders);
        this.updateById(topic);
    }

    /**
     * 批量查询话题信息
     *
     * @return Map<String, List < Topic>>
     */
    public Map<String, List<Topic>> batchGet() {
        List<Topic> topics = this.repository.batchGet();
        if (CollectionUtils.isEmpty(topics)) {
            return new HashMap<>(16);
        }
        return topics.stream().collect(Collectors.groupingBy(Topic::getCategory));
    }

    /**
     * 批量查询话题
     *
     * @param ids ids
     * @return Map<Long, Topic>
     */
    public Map<Long, Topic> batchGet(List<Long> ids) {
        List<Topic> list = this.batchGetByIds(ids);
        if (CollectionUtils.isNotEmpty(list)) {
            return list.stream().collect(Collectors.toMap(Topic::getId, Function.identity()));
        }
        return new HashMap<>(16);
    }

    public List<Topic> searchByName(String name) {
        return this.repository.searchByName(name);
    }

    public List<Topic> getByCategory(String category) {
        return this.repository.getByCategory(category);
    }
}
