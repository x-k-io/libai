package com.kite.libai.provider.message.service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.function.Function;
import java.util.stream.Collectors;

import com.kite.libai.core.repository.BaseService;
import com.kite.libai.provider.message.model.entity.Chat;
import com.kite.libai.provider.message.repository.ChatRepository;
import org.springframework.stereotype.Service;

import org.apache.commons.collections4.CollectionUtils;

import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
@AllArgsConstructor
public class ChatService extends BaseService<ChatRepository, Chat> {

    /**
     * 批量查询会话
     *
     * @param ids ids
     * @return Map<Long, Chat>
     */
    public Map<Long, Chat> batchGet(List<Long> ids) {
        List<Chat> list = this.batchGetByIds(ids);
        if (CollectionUtils.isNotEmpty(list)) {
            return list.stream().collect(Collectors.toMap(Chat::getId, Function.identity()));
        }
        return new HashMap<>(16);
    }
}
