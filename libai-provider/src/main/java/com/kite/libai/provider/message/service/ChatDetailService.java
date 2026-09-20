package com.kite.libai.provider.message.service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.function.Function;
import java.util.stream.Collectors;

import com.kite.libai.core.repository.BaseService;
import com.kite.libai.provider.message.model.entity.ChatDetail;
import com.kite.libai.provider.message.repository.ChatDetailRepository;
import org.springframework.stereotype.Service;

import org.apache.commons.collections4.CollectionUtils;
import com.kite.libai.provider.message.enums.ChatDetailStatus;

import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
@AllArgsConstructor
public class ChatDetailService extends BaseService<ChatDetailRepository, ChatDetail> {

    /**
     * 批量查询会话
     *
     * @param ids ids
     * @return Map<Long, ChatDetail>
     */
    public Map<Long, ChatDetail> batchGet(List<Long> ids) {
        List<ChatDetail> list = super.batchGetByIds(ids);
        if (CollectionUtils.isNotEmpty(list)) {
            return list.stream().collect(Collectors.toMap(ChatDetail::getId, Function.identity()));
        }
        return new HashMap<>(16);
    }

    public ChatDetail getChatDetail(Long accountId, Long friendId) {
        return this.repository.getChatDetail(accountId, friendId);
    }

    public List<ChatDetail> getChatDetails(Long accountId) {
        return this.repository.getChatDetails(accountId);
    }

    public ChatDetail getChatDetailByChatId(Long accountId, Long chatId) {
        return this.repository.getChatDetailByChatId(accountId, chatId);

    }

    public void delete(Long accountId, Long chatId) {
        ChatDetail chatDetail = this.repository.getChatDetailByChatId(accountId, chatId);
        if (chatDetail != null) {
            chatDetail.setStatus(ChatDetailStatus.INVISIBLE.getStatus());
            updateById(chatDetail);
        }
    }

    public void blacklist(Long accountId, Long friendId) {
        ChatDetail chatDetail = this.repository.getChatDetail(accountId, friendId);
        if (chatDetail != null) {
            chatDetail.setStatus(ChatDetailStatus.INVISIBLE.getStatus());
            updateById(chatDetail);
        }
    }

    public List<ChatDetail> getByChatId(Long chatId, String status) {
        return this.repository.getByChatId(chatId, status);
    }
}
