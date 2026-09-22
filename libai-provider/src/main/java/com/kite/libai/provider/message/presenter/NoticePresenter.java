package com.kite.libai.provider.message.presenter;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.function.Function;
import java.util.stream.Collectors;

import jakarta.annotation.PostConstruct;

import com.kite.libai.common.result.PageResult;
import com.kite.libai.common.utils.BeanUtils;
import com.kite.libai.provider.message.model.entity.Notice;
import com.kite.libai.provider.message.model.request.NoticeRequest;
import com.kite.libai.provider.message.model.response.NoticeResponse;
import com.kite.libai.provider.message.service.NoticeService;
import com.kite.libai.provider.message.handle.NoticeHandle;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Component;

import com.kite.libai.common.context.RequestContextUtils;
import org.apache.commons.collections4.CollectionUtils;
import com.kite.libai.provider.message.service.NoticeRemindService;

import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Component
@AllArgsConstructor
public class NoticePresenter {

    private final NoticeService noticeService;

    private final List<NoticeHandle> noticeHandles;

    private Map<String, NoticeHandle> map;

    private final NoticeRemindService noticeRemindService;

    @PostConstruct
    private void init() {
        map = noticeHandles.stream().collect(Collectors.toMap(NoticeHandle::getType, Function.identity()));
    }

    /**
     * 创建通知
     *
     * @param noticeRequest noticeRequest
     */
    @Async
    public void create(NoticeRequest noticeRequest) {
        Notice notice = BeanUtils.copy(noticeRequest, Notice.class);
        noticeService.insert(notice);
        noticeRemindService.add(noticeRequest.getReceiveId(), noticeRequest.getType());
    }

    /**
     * 分页查询通知
     *
     * @param types    types
     * @param pageNum  pageNum
     * @param pageSize pageSize
     * @return PageResult<NoticeResponse>
     */
    public PageResult<NoticeResponse> getNotices(List<String> types, int pageNum, int pageSize) {
        Long accountId = RequestContextUtils.getAccountId();
        PageResult<Notice> pageResult = noticeService.getNotices(accountId, types, pageNum, pageSize);
        noticeRemindService.read(accountId, types);
        List<NoticeResponse> noticeResponses = build(pageResult.getRecords());
        return PageResult.success(noticeResponses, pageResult.getPageCount(), pageResult.getTotal());
    }

    private List<NoticeResponse> build(List<Notice> notices) {
        if (CollectionUtils.isEmpty(notices)) {
            return new ArrayList<>();
        }
        return notices.stream().map(x -> {
            NoticeHandle noticeHandle = map.get(x.getType());
            return noticeHandle.handle(x);
        }).collect(Collectors.toList());
    }
}
