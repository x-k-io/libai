package com.kite.libai.provider.community.assemble;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;

import com.kite.libai.common.utils.BeanUtils;
import com.kite.libai.provider.account.model.entity.Account;
import com.kite.libai.provider.community.model.entity.Reply;
import com.kite.libai.provider.community.model.response.ReplyAdminResponse;
import com.kite.libai.provider.community.model.response.ReplyResponse;
import org.springframework.stereotype.Component;

import com.kite.libai.common.context.RequestContextUtils;
import org.apache.commons.collections4.CollectionUtils;
import com.kite.libai.common.utils.ListUtils;
import com.kite.libai.common.utils.RelativeDateFormat;
import com.kite.libai.provider.account.service.AccountService;
import com.kite.libai.provider.community.service.LikeService;

import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Component
@AllArgsConstructor
public class ReplyAssemble {

    private final LikeService likeService;

    private final AccountService accountService;

    public ReplyResponse toResponse(Reply reply) {
        if (reply == null) {
            return null;
        }
        Set<Long> liked = likeService.liked(RequestContextUtils.getAccountId(), ListUtils.of(reply.getId()));
        ReplyResponse response = BeanUtils.copy(reply, ReplyResponse.class);
        Account account = accountService.getById(reply.getAccountId());
        if (account != null) {
            response.setNickname(account.getNickname());
            response.setAvatar(account.getAvatar());
        }
        Account toAccount = accountService.getById(reply.getToAccountId());
        if (toAccount != null) {
            response.setToNickname(toAccount.getNickname());
        }
        response.setLiked(liked.contains(reply.getId()));
        response.setTimeDescription(RelativeDateFormat.format(reply.getCreatedAt()));
        return response;
    }

    public List<ReplyResponse> toResponse(List<Reply> replies) {
        if (CollectionUtils.isEmpty(replies)) {
            return new ArrayList<>();
        }
        List<Long> accountIds = replies.stream().map(Reply::getAccountId).distinct().collect(Collectors.toList());
        List<Long> toAccountIds = replies.stream().map(Reply::getToAccountId).distinct().collect(Collectors.toList());
        List<Long> replyIds = replies.stream().map(Reply::getId).collect(Collectors.toList());
        accountIds.addAll(toAccountIds);
        Map<Long, Account> accounts = accountService.batchGet(accountIds);
        Long accountId = RequestContextUtils.getAccountId();
        Set<Long> liked = likeService.liked(accountId, replyIds);
        return replies.stream().map(x -> {
            ReplyResponse response = BeanUtils.copy(x, ReplyResponse.class);
            Account account = accounts.get(x.getAccountId());
            if (account != null) {
                response.setNickname(account.getNickname());
                response.setAvatar(account.getAvatar());
            }
            Account toAccount = accounts.get(x.getToAccountId());
            if (toAccount != null) {
                response.setToNickname(toAccount.getNickname());
            }
            response.setLiked(liked.contains(x.getId()));
            response.setTimeDescription(RelativeDateFormat.format(x.getCreatedAt()));
            return response;
        }).collect(Collectors.toList());
    }

    public List<ReplyAdminResponse> toAdminResponse(List<Reply> replies) {
        if (CollectionUtils.isEmpty(replies)) {
            return new ArrayList<>();
        }
        List<Long> accountIds = replies.stream().map(Reply::getAccountId).distinct().collect(Collectors.toList());
        List<Long> toAccountIds = replies.stream().map(Reply::getToAccountId).distinct().collect(Collectors.toList());
        accountIds.addAll(toAccountIds);
        Map<Long, Account> accounts = accountService.batchGet(accountIds);
        return replies.stream().map(x -> {
            ReplyAdminResponse response = BeanUtils.copy(x, ReplyAdminResponse.class);
            Account account = accounts.get(x.getAccountId());
            if (account != null) {
                response.setNickname(account.getNickname());
                response.setAvatar(account.getAvatar());
            }
            Account toAccount = accounts.get(x.getToAccountId());
            if (toAccount != null) {
                response.setToNickname(toAccount.getNickname());
            }
            response.setTimeDescription(RelativeDateFormat.format(x.getCreatedAt()));
            return response;
        }).collect(Collectors.toList());
    }
}
