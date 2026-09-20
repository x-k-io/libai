package com.kite.libai.provider.message.service;

import java.util.List;

import com.kite.libai.core.repository.BaseService;
import com.kite.libai.provider.message.model.entity.NoticeRemind;
import com.kite.libai.provider.message.repository.NoticeRemindRepository;
import org.apache.commons.collections4.CollectionUtils;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

import com.kite.libai.common.utils.ListUtils;
import com.kite.libai.provider.message.enums.NoticeType;

import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
@AllArgsConstructor
public class NoticeRemindService extends BaseService<NoticeRemindRepository, NoticeRemind> {

    public NoticeRemind getByAccountId(Long accountId) {
        return this.repository.getByAccountId(accountId);
    }

    @Async
    public void read(Long accountId, String type) {
        read(accountId, ListUtils.of(type));
    }

    @Async
    public void read(Long accountId, List<String> types) {
        if (CollectionUtils.isEmpty(types)) {
            return;
        }
        NoticeRemind noticeRemind = getByAccountId(accountId);
        if (noticeRemind == null) {
            return;
        }
        for (String type : types) {
            NoticeType noticeType = NoticeType.getByType(type);
            if (noticeType == null) {
                continue;
            }
            switch (noticeType) {
                case SYSTEM:
                    noticeRemind.setSystem(0);
                    break;
                case LIKE:
                    noticeRemind.setLikes(0);
                    break;
                case FORWARD:
                    noticeRemind.setForwards(0);
                    break;
                case REPLY:
                    noticeRemind.setReplies(0);
                    break;
                case FOLLOW:
                    noticeRemind.setFollows(0);
                    break;
                default:
                    break;
            }
        }
        updateById(noticeRemind);
    }

    public void add(Long accountId, String type) {
        NoticeType noticeType = NoticeType.getByType(type);
        if (noticeType == null || accountId == null) {
            return;
        }
        NoticeRemind noticeRemind = getByAccountId(accountId);
        if (noticeRemind == null) {
            noticeRemind = new NoticeRemind();
            noticeRemind.setAccountId(accountId);
            noticeRemind.setSystem(0);
            noticeRemind.setLikes(0);
            noticeRemind.setForwards(0);
            noticeRemind.setReplies(0);
            noticeRemind.setFollows(0);
            save(noticeRemind);
            return;
        }
        switch (noticeType) {
            case SYSTEM:
                noticeRemind.setSystem(noticeRemind.getSystem() + 1);
                break;
            case LIKE:
                noticeRemind.setLikes(noticeRemind.getLikes() + 1);
                break;
            case FORWARD:
                noticeRemind.setForwards(noticeRemind.getForwards() + 1);
                break;
            case REPLY:
                noticeRemind.setReplies(noticeRemind.getReplies() + 1);
                break;
            case FOLLOW:
                noticeRemind.setFollows(noticeRemind.getFollows() + 1);
                break;
            default:
                break;
        }
        updateById(noticeRemind);
    }
}
