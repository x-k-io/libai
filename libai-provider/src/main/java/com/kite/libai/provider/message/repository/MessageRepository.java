package com.kite.libai.provider.message.repository;

import com.kite.libai.core.repository.BaseRepository;
import com.kite.libai.provider.message.model.entity.Message;

import java.time.LocalDateTime;
import java.util.List;

public interface MessageRepository extends BaseRepository<Message> {
    List<Message> getMessages(Long chatId, LocalDateTime lastMsgTime);

    List<Message> getMessagesByLastId(Long chatId, Long lastId, int limit);
}
