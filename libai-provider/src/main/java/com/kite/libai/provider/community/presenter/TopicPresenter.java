package com.kite.libai.provider.community.presenter;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import com.kite.libai.provider.community.assemble.TopicAssemble;
import com.kite.libai.provider.community.model.entity.Topic;
import com.kite.libai.provider.community.model.request.TopicRequest;
import com.kite.libai.provider.community.model.response.TopicCategoryResponse;
import com.kite.libai.provider.community.model.response.TopicResponse;

import org.springframework.stereotype.Component;

import com.kite.libai.common.exception.ServiceException;
import com.kite.libai.provider.community.enums.TopicCategory;
import com.kite.libai.provider.community.service.TopicService;

import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Component
@AllArgsConstructor
public class TopicPresenter {

    private final TopicService topicService;

    private final TopicAssemble topicAssemble;

    /**
     * 管理端创建话题
     *
     * @param request request
     * @return boolean
     */
    public boolean create(TopicRequest request) {
        Topic check = topicService.getByName(request.getName());
        if (check != null) {
            return Boolean.TRUE;
        }
        Topic topic = new Topic();
        topic.setCategory(request.getCategory());
        topic.setName(request.getName());
        topic.setIcon(request.getIcon());
        topic.setDescription(request.getDescription());
        topic.setOrders(System.currentTimeMillis());
        return topicService.insert(topic);
    }

    /**
     * 用户端创建话题
     *
     * @param name name
     * @return boolean
     */
    public boolean create(String name) {
        Topic check = topicService.getByName(name);
        if (check != null) {
            return Boolean.TRUE;
        }
        Topic topic = new Topic();
        topic.setCategory(TopicCategory.OTHER.getCode());
        topic.setName(name);
        topic.setOrders(System.currentTimeMillis());
        return topicService.insert(topic);
    }

    /**
     * 更新话题
     *
     * @param id      id
     * @param request request
     * @return boolean
     */
    public boolean updateById(Long id, TopicRequest request) {
        Topic topic = topicService.getById(id);
        if (topic == null) {
            throw new ServiceException("话题不存在");
        }
        topic.setDescription(request.getDescription());
        return topicService.updateById(topic);
    }

    /**
     * 修改排序
     *
     * @param ids ids
     */
    public void updateOrders(List<Long> ids) {
        for (Long id : ids) {
            topicService.updateOrders(id, System.currentTimeMillis());
        }
    }

    /**
     * 查询话题列表
     *
     * @return List<TopicResponse>
     */
    public List<TopicResponse> getTopicList(String category) {
        List<Topic> list = topicService.getByCategory(category);
        return topicAssemble.toResponse(list);
    }

    /**
     * 查询话题列表
     *
     * @return List<ChannelCategoryResponse>
     */
    public List<TopicCategoryResponse> getTopics() {
        List<TopicCategoryResponse> responses = new ArrayList<>();
        Map<String, List<Topic>> topicsMap = topicService.batchGet();
        for (TopicCategory category : TopicCategory.values()) {
            if (!category.isEnabled()) {
                continue;
            }
            TopicCategoryResponse response = new TopicCategoryResponse();
            response.setCategory(category.getCode());
            response.setName(category.getName());
            List<Topic> topics = topicsMap.get(category.getCode());
            response.setTopics(topicAssemble.toResponse(topics));
            responses.add(response);
        }
        return responses;
    }

    /**
     * 搜索话题
     *
     * @return List<TopicResponse>
     */
    public List<TopicResponse> search(String name) {
        List<Topic> list = topicService.searchByName(name);
        return topicAssemble.toResponse(list);
    }

    /**
     * 查询话题分类列表
     *
     * @return List<ChannelCategoryResponse>
     */
    public List<TopicCategoryResponse> getTopicCategories() {
        List<TopicCategoryResponse> responses = new ArrayList<>();
        for (TopicCategory category : TopicCategory.values()) {
            TopicCategoryResponse response = new TopicCategoryResponse();
            response.setCategory(category.getCode());
            response.setName(category.getName());
            responses.add(response);
        }
        return responses;
    }
}
