package com.kite.libai.provider.community.assemble;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import com.kite.libai.core.utils.BeanUtils;
import com.kite.libai.provider.community.model.entity.Topic;
import com.kite.libai.provider.community.model.request.TopicRequest;
import com.kite.libai.provider.community.model.response.TopicResponse;
import org.springframework.stereotype.Component;

import org.apache.commons.collections4.CollectionUtils;

import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Component
@AllArgsConstructor
public class TopicAssemble {

    public Topic toEntity(TopicRequest topicRequest) {
        if (topicRequest == null) {
            return null;
        }
        Topic topic = BeanUtils.copy(topicRequest, Topic.class);
        return topic;
    }

    public TopicResponse toResponse(Topic topic) {
        if (topic == null) {
            return null;
        }
        TopicResponse response = BeanUtils.copy(topic, TopicResponse.class);
        return response;
    }

    public List<TopicResponse> toResponse(List<Topic> topics) {
        if (CollectionUtils.isEmpty(topics)) {
            return new ArrayList<>();
        }
        List<TopicResponse> responses = topics.stream().map(topic -> {
            TopicResponse response = BeanUtils.copy(topic, TopicResponse.class);
            return response;
        }).collect(Collectors.toList());
        return responses;
    }
}
