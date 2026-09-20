package com.kite.libai.provider.message.service;

import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.function.Function;
import java.util.stream.Collectors;

import com.kite.libai.core.repository.BaseService;
import com.kite.libai.provider.message.model.entity.Message;
import com.kite.libai.provider.message.repository.MessageRepository;
import org.springframework.stereotype.Service;

import org.apache.commons.collections4.CollectionUtils;

import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
@AllArgsConstructor
public class MessageService extends BaseService<MessageRepository, Message> {

    /**
     * 批量查询消息
     *
     * @param ids ids
     * @return Map<Long, Message>
     */
    public Map<Long, Message> batchGet(List<Long> ids) {
        List<Message> list = this.batchGetByIds(ids);
        if (CollectionUtils.isNotEmpty(list)) {
            return list.stream().collect(Collectors.toMap(Message::getId, Function.identity()));
        }
        return new HashMap<>(16);
    }

    public List<Message> getMessages(Long chatId, LocalDateTime lastMsgTime) {
        return this.repository.getMessages(chatId, lastMsgTime);
    }

    public List<Message> getMessagesByLastId(Long chatId, Long lastId, int limit) {
        return this.repository.getMessagesByLastId(chatId, lastId, limit);
    }
}
