package com.kite.libai.provider.message.presenter;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import com.kite.libai.core.utils.BeanUtils;
import com.kite.libai.provider.account.model.entity.Account;
import com.kite.libai.provider.account.service.AccountService;
import com.kite.libai.provider.community.model.entity.Friend;
import com.kite.libai.provider.community.service.BlacklistService;
import com.kite.libai.provider.community.service.FriendService;
import com.kite.libai.provider.message.model.entity.Chat;
import com.kite.libai.provider.message.model.entity.ChatDetail;
import com.kite.libai.provider.message.model.entity.Message;
import com.kite.libai.provider.message.model.request.ChatRequest;
import com.kite.libai.provider.message.model.request.MessageRequest;
import com.kite.libai.provider.message.model.response.ChatResponse;
import com.kite.libai.provider.message.model.response.MessageResponse;
import com.kite.libai.provider.message.service.MessageService;
import com.kite.libai.provider.message.service.ChatService;
import org.springframework.stereotype.Component;

import com.kite.libai.core.context.RequestContextUtils;
import com.kite.libai.core.exception.ServiceException;
import com.kite.libai.core.result.SystemCode;
import org.apache.commons.collections4.CollectionUtils;
import com.kite.libai.provider.message.enums.ChatDetailStatus;
import com.kite.libai.provider.message.enums.RelationType;
import com.kite.libai.provider.message.service.ChatDetailService;

import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Component
@AllArgsConstructor
public class ChatPresenter {

    private final ChatService chatService;

    private final FriendService friendService;

    private final BlacklistService blacklistService;

    private final AccountService accountService;

    private final MessageService messageService;

    private final ChatDetailService chatDetailService;

    /**
     * 创建会话
     *
     * @param chatRequest chatRequest
     * @return boolean
     */
    public ChatResponse create(ChatRequest chatRequest) {
        Long accountId = RequestContextUtils.getAccountId();
        Long friendId = chatRequest.getFriendId();
        Account friend = accountService.getById(friendId);
        if (friend == null) {
            throw new ServiceException("查询好友信息异常");
        }
        Friend me = friendService.getFriend(accountId, friendId);
        if (me == null) {
            throw new ServiceException("您还未关注" + friend.getNickname() + "！");
        }
        boolean check = blacklistService.check(accountId, friendId);
        if (check) {
            throw new ServiceException(friend.getNickname() + "已被您拉黑,请移出黑名单后再发起聊天！");
        }
        check = blacklistService.check(friendId, accountId);
        if (check) {
            throw new ServiceException("您已被" + friend.getNickname() + "拉黑！");
        }
        ChatDetail chatDetail = chatDetailService.getChatDetail(accountId, friendId);
        Chat chat;
        if (chatDetail == null) {
            chat = new Chat();
            chatService.save(chat);
            chatDetail = new ChatDetail();
            chatDetail.setAccountId(accountId);
            chatDetail.setFriendId(friendId);
            chatDetail.setChatId(chat.getId());
            chatDetail.setRelation(RelationType.FRIEND.getType());
            chatDetail.setStatus(ChatDetailStatus.VISIBLE.getStatus());
            ChatDetail friendChatDetail = new ChatDetail();
            friendChatDetail.setAccountId(friendId);
            friendChatDetail.setFriendId(accountId);
            friendChatDetail.setChatId(chat.getId());
            if (me.getFriend()) {
                friendChatDetail.setRelation(RelationType.FRIEND.getType());
            } else {
                friendChatDetail.setRelation(RelationType.STRANGER.getType());
            }
            friendChatDetail.setStatus(ChatDetailStatus.VISIBLE.getStatus());
            chatDetailService.save(chatDetail);
            chatDetailService.save(friendChatDetail);
        } else {
            if (ChatDetailStatus.INVISIBLE.getStatus().equals(chatDetail.getStatus())) {
                chatDetail.setStatus(ChatDetailStatus.VISIBLE.getStatus());
                chatDetailService.updateById(chatDetail);
            }
            chat = chatService.getById(chatDetail.getChatId());
        }
        ChatResponse response = new ChatResponse();
        response.setId(chat.getId());
        response.setAccountId(friendId);
        response.setNickname(friend.getNickname());
        response.setAvatar(friend.getAvatar());
        response.setRelation(chatDetail.getRelation());
        response.setLastMsgId(chat.getLastMsgId());
        response.setLastContent(chat.getLastContent());
        response.setLastedAt(chat.getLastedAt());
        response.setCreatedAt(chat.getCreatedAt());
        return response;
    }

    public List<ChatResponse> getChats(Long accountId) {
        List<ChatDetail> chatDetails = chatDetailService.getChatDetails(accountId);
        if (CollectionUtils.isEmpty(chatDetails)) {
            return new ArrayList<>();
        }
        List<Long> chatIds = chatDetails.stream().map(ChatDetail::getChatId).collect(Collectors.toList());
        Map<Long, Chat> chats = chatService.batchGet(chatIds);
        return chatDetails.stream().map(x -> {
            ChatResponse response = new ChatResponse();
            response.setId(x.getChatId());
            response.setAccountId(x.getFriendId());
            Account account = accountService.getById(x.getFriendId());
            if (account != null) {
                response.setNickname(account.getNickname());
                response.setAvatar(account.getAvatar());
            }
            response.setRelation(x.getRelation());
            response.setUnread(x.getUnread());
            Chat chat = chats.get(x.getChatId());
            if (chat != null) {

                response.setLastMsgId(chat.getLastMsgId());
                response.setLastContent(chat.getLastContent());
                response.setLastedAt(chat.getLastedAt());
                response.setCreatedAt(chat.getCreatedAt());
            }
            return response;
        }).collect(Collectors.toList());
    }

    public void delete(Long accountId, Long chatId) {
        chatDetailService.delete(accountId, chatId);
    }

    public void sendMessage(Long chatId, MessageRequest messageRequest) {
        Long accountId = RequestContextUtils.getAccountId();
        Chat chat = chatService.getById(chatId);
        if (chat == null) {
            return;
        }
        // 如果存在不可见的会话，设置为可见
        List<ChatDetail> chatDetails = chatDetailService.getByChatId(chatId, null);
        if (CollectionUtils.isNotEmpty(chatDetails)) {
            chatDetails.forEach(x -> {
                if (!x.getAccountId().equals(accountId)) {
                    x.setStatus(ChatDetailStatus.VISIBLE.getStatus());
                    x.setUnread(x.getUnread() + 1);
                    chatDetailService.updateById(x);
                }
            });
        }
        Message message = BeanUtils.copy(messageRequest, Message.class);
        message.setChatId(chatId);
        message.setAccountId(accountId);
        messageService.save(message);
        // 更新会话信息
        chat.setLastMsgId(message.getId());
        chat.setLastContent(message.getContent());
        chat.setLastedAt(message.getCreatedAt());
        chatService.updateById(chat);
    }

    public List<MessageResponse> getMessages(Long chatId, LocalDateTime lastMsgTime) {
        Long accountId = RequestContextUtils.getAccountId();
        ChatDetail chatDetail = chatDetailService.getChatDetailByChatId(accountId, chatId);
        List<Message> messages = messageService.getMessages(chatId, lastMsgTime);
        chatDetail.setUnread(0);
        chatDetailService.updateById(chatDetail);
        return buildMessage(messages);
    }

    public List<MessageResponse> getMessageRecords(Long chatId, Long lastId, int limit) {
        Long accountId = RequestContextUtils.getAccountId();
        ChatDetail chatDetail = chatDetailService.getChatDetailByChatId(accountId, chatId);
        List<Message> messages = messageService.getMessagesByLastId(chatId, lastId, limit);
        chatDetail.setUnread(0);
        chatDetailService.updateById(chatDetail);
        return buildMessage(messages);
    }

    private List<MessageResponse> buildMessage(List<Message> messages) {
        if (CollectionUtils.isEmpty(messages)) {
            return new ArrayList<>();
        }
        List<Long> accountIds = messages.stream().map(Message::getAccountId).distinct().collect(Collectors.toList());
        Map<Long, Account> accounts = accountService.batchGet(accountIds);
        return messages.stream().map(x -> {
            MessageResponse response = BeanUtils.copy(x, MessageResponse.class);
            Account account = accounts.get(x.getAccountId());
            if (account != null) {
                response.setNickname(account.getNickname());
                response.setAvatar(account.getAvatar());
            }
            return response;
        }).collect(Collectors.toList());
    }
}
