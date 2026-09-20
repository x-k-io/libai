package com.kite.libai.provider.message.repository.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.kite.libai.core.repository.MybatisBaseRepository;
import com.kite.libai.provider.message.mapper.MessageMapper;
import com.kite.libai.provider.message.model.entity.Message;
import com.kite.libai.provider.message.repository.MessageRepository;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;
import java.util.List;

@Repository
public class MessageRepositoryImpl extends MybatisBaseRepository<MessageMapper, Message> implements MessageRepository {
    @Override
    public List<Message> getMessages(Long chatId, LocalDateTime lastMsgTime) {
        LambdaQueryWrapper<Message> wrapper = new LambdaQueryWrapper<>();
        wrapper.eq(Message::getChatId, chatId).gt(Message::getCreatedAt, lastMsgTime);
        wrapper.orderByAsc(Message::getCreatedAt);
        return this.baseMapper.selectList(wrapper);
    }

    @Override
    public List<Message> getMessagesByLastId(Long chatId, Long lastId, int limit) {
        LambdaQueryWrapper<Message> wrapper = new LambdaQueryWrapper<>();
        wrapper.eq(Message::getChatId, chatId);
        if (lastId != null) {
            wrapper.lt(Message::getId, lastId);
        }
        wrapper.orderByDesc(Message::getCreatedAt);
        wrapper.last(" limit " + limit);
        return this.baseMapper.selectList(wrapper);
    }
}
