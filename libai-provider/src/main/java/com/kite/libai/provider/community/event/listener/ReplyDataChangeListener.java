package com.kite.libai.provider.community.event.listener;

import com.kite.libai.provider.community.service.ReplyService;
import org.springframework.stereotype.Component;

import com.google.common.eventbus.Subscribe;
import com.kite.libai.core.event.core.BaseListener;
import com.kite.libai.provider.community.enums.DataType;
import com.kite.libai.provider.community.enums.EntityType;
import com.kite.libai.provider.community.enums.LikeEventType;
import com.kite.libai.provider.community.event.model.LikeEvent;

import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Component
@AllArgsConstructor
public class ReplyDataChangeListener implements BaseListener {

    private final ReplyService replyService;

    @Subscribe
    public void process(LikeEvent likeEvent) {
        if (likeEvent == null) {
            return;
        }
        if (EntityType.REPLY.getType().equals(likeEvent.getEntityType())) {
            if (LikeEventType.LIKED.getType().equals(likeEvent.getType())) {
                replyService.add(likeEvent.getEntityId(), DataType.LIKES);
            } else {
                replyService.reduce(likeEvent.getEntityId(), DataType.LIKES);
            }
        }
    }
}
