package com.kite.libai.provider.message.repository.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.kite.libai.core.repository.MybatisBaseRepository;
import com.kite.libai.provider.message.enums.ChatDetailStatus;

import org.apache.commons.lang3.StringUtils;
import com.kite.libai.provider.message.mapper.ChatDetailMapper;
import com.kite.libai.provider.message.model.entity.ChatDetail;
import com.kite.libai.provider.message.repository.ChatDetailRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class ChatDetailRepositoryImpl extends MybatisBaseRepository<ChatDetailMapper, ChatDetail> implements ChatDetailRepository {

    public ChatDetail getChatDetail(Long accountId, Long friendId) {
        LambdaQueryWrapper<ChatDetail> wrapper = new LambdaQueryWrapper<>();
        wrapper.eq(ChatDetail::getAccountId, accountId).eq(ChatDetail::getFriendId, friendId);
        return this.baseMapper.selectOne(wrapper);
    }

    @Override
    public List<ChatDetail> getChatDetails(Long accountId) {
        LambdaQueryWrapper<ChatDetail> wrapper = new LambdaQueryWrapper<>();
        wrapper.eq(ChatDetail::getAccountId, accountId).eq(ChatDetail::getStatus, ChatDetailStatus.VISIBLE.getStatus());
        return this.baseMapper.selectList(wrapper);
    }

    @Override
    public ChatDetail getChatDetailByChatId(Long accountId, Long chatId) {
        LambdaQueryWrapper<ChatDetail> wrapper = new LambdaQueryWrapper<>();
        wrapper.eq(ChatDetail::getAccountId, accountId).eq(ChatDetail::getChatId, chatId);
        return this.baseMapper.selectOne(wrapper);
    }

    @Override
    public List<ChatDetail> getByChatId(Long chatId, String status) {
        LambdaQueryWrapper<ChatDetail> wrapper = new LambdaQueryWrapper<>();
        wrapper.eq(ChatDetail::getChatId, chatId);
        if (StringUtils.isNotBlank(status)) {
            wrapper.eq(ChatDetail::getStatus, status);
        }
        return this.baseMapper.selectList(wrapper);
    }
}
