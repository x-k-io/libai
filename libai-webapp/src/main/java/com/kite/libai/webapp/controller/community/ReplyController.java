package com.kite.libai.webapp.controller.community;

import com.kite.libai.core.context.RequestContextUtils;
import com.kite.libai.core.result.PageResult;
import com.kite.libai.core.result.Result;
import com.kite.libai.provider.community.model.request.ReplyRequest;
import com.kite.libai.provider.community.model.response.ReplyResponse;
import com.kite.libai.provider.community.presenter.ReplyPresenter;
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
@RequestMapping(value = "/api/app/v1/replies")
public class ReplyController {

    private final ReplyPresenter replyPresenter;

    @PostMapping
    @KitePermission
    public Result<ReplyResponse> reply(@Valid @RequestBody ReplyRequest replyRequest) {
        replyRequest.setAudit(Boolean.TRUE);
        replyRequest.setAccountId(RequestContextUtils.getAccountId());
        ReplyResponse response = replyPresenter.create(replyRequest);
        return Result.success(response);
    }

    @GetMapping
    public PageResult<ReplyResponse> page(
            @RequestParam(required = false) Long commentId,
            @RequestParam(required = false, defaultValue = "0") int pageNum,
            @RequestParam(required = false, defaultValue = "10") int pageSize) {
        return replyPresenter.getByCommentId(commentId, pageNum, pageSize);
    }
}
