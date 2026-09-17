package com.kite.libai.provider.message.repository.impl;

import com.kite.libai.boot.repository.MybatisBaseRepository;
import com.kite.libai.provider.message.mapper.ChatMapper;
import com.kite.libai.provider.message.model.entity.Chat;
import com.kite.libai.provider.message.repository.ChatRepository;
import org.springframework.stereotype.Repository;

@Repository
public class ChatRepositoryImpl extends MybatisBaseRepository<ChatMapper, Chat> implements ChatRepository {
}
