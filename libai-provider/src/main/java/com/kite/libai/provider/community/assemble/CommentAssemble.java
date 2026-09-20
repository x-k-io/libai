package com.kite.libai.provider.community.assemble;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;

import com.kite.libai.common.utils.BeanUtils;
import com.kite.libai.provider.account.model.entity.Account;
import com.kite.libai.provider.community.model.entity.Comment;
import com.kite.libai.provider.community.model.response.CommentAdminResponse;
import com.kite.libai.provider.community.model.response.CommentResponse;
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
public class CommentAssemble {

    private final LikeService likeService;

    private final AccountService accountService;

    public List<CommentResponse> toResponse(List<Comment> comments) {
        if (CollectionUtils.isEmpty(comments)) {
            return new ArrayList<>();
        }
        List<Long> accountIds = comments.stream().map(Comment::getAccountId).distinct().collect(Collectors.toList());
        List<Long> commentIds = comments.stream().map(Comment::getId).collect(Collectors.toList());
        Map<Long, Account> accounts = accountService.batchGet(accountIds);
        Set<Long> liked = likeService.liked(RequestContextUtils.getAccountId(), commentIds);
        return comments.stream().map(x -> {
            CommentResponse response = BeanUtils.copy(x, CommentResponse.class);
            Account account = accounts.get(x.getAccountId());
            if (account != null) {
                response.setNickname(account.getNickname());
                response.setAvatar(account.getAvatar());
            }
            response.setLiked(liked.contains(x.getId()));
            response.setTimeDescription(RelativeDateFormat.format(x.getCreatedAt()));
            return response;
        }).collect(Collectors.toList());
    }

    public CommentResponse toResponse(Comment comment) {
        if (comment == null) {
            return null;
        }
        Account account = accountService.getById(comment.getAccountId());
        Set<Long> liked =
                likeService.liked(RequestContextUtils.getAccountId(), ListUtils.of(comment.getId()));
        CommentResponse response = BeanUtils.copy(comment, CommentResponse.class);
        if (account != null) {
            response.setNickname(account.getNickname());
            response.setAvatar(account.getAvatar());
        }
        response.setLiked(liked.contains(comment.getId()));
        response.setTimeDescription(RelativeDateFormat.format(comment.getCreatedAt()));
        return response;
    }

    public List<CommentAdminResponse> toAdminResponse(List<Comment> comments) {
        if (CollectionUtils.isEmpty(comments)) {
            return new ArrayList<>();
        }
        List<Long> accountIds = comments.stream().map(Comment::getAccountId).distinct().collect(Collectors.toList());
        Map<Long, Account> accounts = accountService.batchGet(accountIds);
        return comments.stream().map(x -> {
            CommentAdminResponse response = BeanUtils.copy(x, CommentAdminResponse.class);
            Account account = accounts.get(x.getAccountId());
            if (account != null) {
                response.setNickname(account.getNickname());
                response.setAvatar(account.getAvatar());
            }
            response.setTimeDescription(RelativeDateFormat.format(x.getCreatedAt()));
            return response;
        }).collect(Collectors.toList());
    }

}
