package com.kite.libai.provider.message.repository;

import com.kite.libai.boot.repository.BaseRepository;
import com.kite.libai.provider.message.model.entity.ChatDetail;

import java.util.List;

public interface ChatDetailRepository extends BaseRepository<ChatDetail> {
    ChatDetail getChatDetail(Long accountId, Long friendId);

    List<ChatDetail> getChatDetails(Long accountId);

    ChatDetail getChatDetailByChatId(Long accountId, Long chatId);

    List<ChatDetail> getByChatId(Long chatId, String status);
}
