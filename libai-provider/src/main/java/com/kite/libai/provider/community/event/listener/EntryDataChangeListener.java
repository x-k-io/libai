package com.kite.libai.provider.community.event.listener;

import com.kite.libai.provider.community.enums.CommentEventType;
import com.kite.libai.provider.community.enums.DataType;
import com.kite.libai.provider.community.enums.EntityType;
import com.kite.libai.provider.community.enums.FavoriteEventType;
import com.kite.libai.provider.community.enums.LikeEventType;
import com.kite.libai.provider.community.enums.ReplyEventType;
import com.kite.libai.provider.community.event.model.BrowseEvent;
import com.kite.libai.provider.community.event.model.CommentEvent;
import com.kite.libai.provider.community.event.model.FavoriteEvent;
import com.kite.libai.provider.community.event.model.LikeEvent;
import com.kite.libai.provider.community.event.model.PlayEvent;
import com.kite.libai.provider.community.event.model.ReplyEvent;
import com.kite.libai.provider.community.model.entity.Comment;
import com.kite.libai.provider.community.model.entity.Entry;
import com.kite.libai.provider.community.service.EntryService;
import org.springframework.stereotype.Component;

import com.google.common.eventbus.Subscribe;
import com.kite.libai.boot.event.core.BaseListener;

import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Component
@AllArgsConstructor
public class EntryDataChangeListener implements BaseListener {

    private final EntryService entryService;

    /**
     * 累计评论量数据
     *
     * @param commentEvent commentEvent
     */
    @Subscribe
    public void process(CommentEvent commentEvent) {
        if (commentEvent == null) {
            return;
        }
        Comment comment = commentEvent.getComment();
        if (comment == null) {
            return;
        }
        Entry entry = entryService.getById(comment.getEntryId());
        if (entry == null) {
            return;
        }
        if (CommentEventType.CREATED.getType().equals(commentEvent.getType())) {
            entryService.add(entry.getId(), DataType.REPLIES);
        } else {
            entryService.reduce(entry.getId(), DataType.REPLIES);
        }
    }

    /**
     * 累计回复量数据
     *
     * @param replyEvent replyEvent
     */
    @Subscribe
    public void process(ReplyEvent replyEvent) {
        if (replyEvent == null) {
            return;
        }
        if (ReplyEventType.CREATED.getType().equals(replyEvent.getType())) {
            entryService.add(replyEvent.getEntryId(), DataType.REPLIES);
        } else {
            entryService.reduce(replyEvent.getEntryId(), DataType.REPLIES);
        }
    }

    /**
     * 累计点赞量数据
     *
     * @param likeEvent likeEvent
     */
    @Subscribe
    public void process(LikeEvent likeEvent) {
        if (likeEvent == null) {
            return;
        }
        if (EntityType.ENTRY.getType().equals(likeEvent.getEntityType())) {
            Entry entry = entryService.getById(likeEvent.getEntityId());
            if (entry == null) {
                return;
            }
            if (LikeEventType.LIKED.getType().equals(likeEvent.getType())) {
                entryService.add(likeEvent.getEntityId(), DataType.LIKES);
            } else {
                entryService.reduce(likeEvent.getEntityId(), DataType.LIKES);
            }
        }
    }

    /**
     * 累计浏览量数据
     *
     * @param browseEvent browseEvent
     */
    @Subscribe
    public void process(BrowseEvent browseEvent) {
        if (browseEvent == null) {
            return;
        }
        entryService.add(browseEvent.getEntryId(), DataType.BROWSES);
    }

    /**
     * 累计收藏量数据
     *
     * @param favoriteEvent favoriteEvent
     */
    @Subscribe
    public void process(FavoriteEvent favoriteEvent) {
        if (favoriteEvent == null) {
            return;
        }
        if (FavoriteEventType.FAVORITES.getType().equals(favoriteEvent.getType())) {
            entryService.add(favoriteEvent.getEntryId(), DataType.FAVORITES);
        } else {
            entryService.reduce(favoriteEvent.getEntryId(), DataType.FAVORITES);
        }
    }

    /**
     * 累计播放量数据
     *
     * @param playEvent playEvent
     */
    @Subscribe
    public void process(PlayEvent playEvent) {
        if (playEvent == null) {
            return;
        }
        entryService.add(playEvent.getEntryId(), DataType.PLAYS);
    }
}
