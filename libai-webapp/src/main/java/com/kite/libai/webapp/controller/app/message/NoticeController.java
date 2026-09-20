package com.kite.libai.webapp.controller.app.message;

import com.kite.libai.core.context.RequestContextUtils;
import com.kite.libai.core.result.PageResult;
import com.kite.libai.core.result.Result;
import com.kite.libai.provider.message.model.response.NoticeRemindResponse;
import com.kite.libai.provider.message.model.response.NoticeResponse;
import com.kite.libai.provider.message.presenter.NoticePresenter;
import com.kite.libai.provider.message.presenter.NoticeRemindPresenter;
import com.kite.libai.webapp.annotation.KitePermission;

import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@Slf4j
@Validated
@RestController
@AllArgsConstructor
@RequestMapping(value = "/api/app/v1/notices")
public class NoticeController {

    private final NoticePresenter noticePresenter;

    private final NoticeRemindPresenter noticeRemindPresenter;

    @GetMapping
    @KitePermission
    public PageResult<NoticeResponse> getNotices(
            @RequestParam(required = false) List<String> types,
            @RequestParam(required = false, defaultValue = "1") Integer pageNum,
            @RequestParam(required = false, defaultValue = "10") Integer pageSize) {
        return noticePresenter.getNotices(types, pageNum, pageSize);
    }

    @GetMapping(value = "un-read")
    @KitePermission
    public Result<NoticeRemindResponse> unread() {
        NoticeRemindResponse noticeRemind = noticeRemindPresenter.getByAccountId(RequestContextUtils.getAccountId());
        return Result.success(noticeRemind);
    }
}
