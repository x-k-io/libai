package com.kite.libai.webapp.controller.message;

import com.kite.libai.core.context.RequestContextUtils;
import com.kite.libai.core.result.Result;
import com.kite.libai.provider.message.model.request.ChatRequest;
import com.kite.libai.provider.message.model.request.MessageRequest;
import com.kite.libai.provider.message.model.response.ChatResponse;
import com.kite.libai.provider.message.model.response.MessageResponse;
import com.kite.libai.provider.message.presenter.ChatPresenter;
import com.kite.libai.security.annotation.KitePermission;

import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;
import java.time.LocalDateTime;
import java.util.List;

@Slf4j
@Validated
@RestController
@AllArgsConstructor
@RequestMapping(value = "/api/app/v1/chats")
public class ChatController {

    private final ChatPresenter chatPresenter;

    @PostMapping
    @KitePermission
    public Result<ChatResponse> create(
            @Valid @RequestBody ChatRequest chatRequest) {
        ChatResponse response = chatPresenter.create(chatRequest);
        return Result.success(response);
    }

    @DeleteMapping(value = "/{id}")
    @KitePermission
    public Result<Object> delete(@PathVariable Long id) {
        chatPresenter.delete(RequestContextUtils.getAccountId(), id);
        return Result.success();
    }

    @GetMapping
    @KitePermission
    public Result<List<ChatResponse>> getChatList() {
        List<ChatResponse> list = chatPresenter.getChats(RequestContextUtils.getAccountId());
        return Result.success(list);
    }


    @PostMapping(value = "/{id}/messages")
    @KitePermission
    public Result<Object> sendMessage(
            @PathVariable Long id,
            @Valid @RequestBody MessageRequest messageRequest) {
        chatPresenter.sendMessage(id, messageRequest);
        return Result.success();
    }


    @GetMapping(value = "/{id}/messages")
    @KitePermission
    public Result<List<MessageResponse>> getMessages(
            @PathVariable Long id,
            @RequestParam LocalDateTime lastRefreshTime) {
        List<MessageResponse> responses = chatPresenter.getMessages(id, lastRefreshTime);
        return Result.success(responses);
    }


    @GetMapping(value = "/{id}/message-records")
    @KitePermission
    public Result<List<MessageResponse>> getMessageRecords(
            @PathVariable Long id,
            @RequestParam(required = false) Long lastId,
            @RequestParam(required = false, defaultValue = "10") Integer limit) {
        List<MessageResponse> responses = chatPresenter.getMessageRecords(id, lastId, limit);
        return Result.success(responses);
    }
}
