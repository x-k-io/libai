package com.kite.libai.webapp.controller.community;

import com.kite.libai.core.context.RequestContextUtils;
import com.kite.libai.core.result.PageResult;
import com.kite.libai.core.result.Result;
import com.kite.libai.provider.community.model.request.CommentRequest;
import com.kite.libai.provider.community.model.response.CommentResponse;
import com.kite.libai.provider.community.presenter.CommentPresenter;
import com.kite.libai.security.annotation.KitePermission;

import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import jakarta.validation.Valid;

@Slf4j
@Validated
@RestController
@AllArgsConstructor
@RequestMapping(value = "/api/app/v1/comments")
public class CommentController {

    private final CommentPresenter commentPresenter;

    @PostMapping
    @KitePermission
    public Result<CommentResponse> comment(
            @Valid @RequestBody CommentRequest commentRequest) {
        commentRequest.setAudit(Boolean.TRUE);
        commentRequest.setAccountId(RequestContextUtils.getAccountId());
        CommentResponse response = commentPresenter.create(commentRequest);
        return Result.success(response);
    }

    @GetMapping
    public PageResult<CommentResponse> getComments(
            @RequestParam(required = false) Long entryId,
            @RequestParam(required = false, defaultValue = "0") int pageNum,
            @RequestParam(required = false, defaultValue = "10") int pageSize) {
        return commentPresenter.getByEntryId(entryId, pageNum, pageSize);
    }
}
