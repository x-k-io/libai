package com.kite.libai.provider.community.event.listener;

import com.kite.libai.provider.community.enums.CommentEventType;
import com.kite.libai.provider.community.enums.DataType;
import com.kite.libai.provider.community.enums.EntryEventType;
import com.kite.libai.provider.community.enums.FollowerEventType;
import com.kite.libai.provider.community.enums.LikeEventType;
import com.kite.libai.provider.community.enums.ReplyEventType;
import com.kite.libai.provider.community.event.model.BrowseEvent;
import com.kite.libai.provider.community.event.model.CommentEvent;
import com.kite.libai.provider.community.event.model.EntryEvent;
import com.kite.libai.provider.community.event.model.FollowerEvent;
import com.kite.libai.provider.community.event.model.LikeEvent;
import com.kite.libai.provider.community.event.model.PlayEvent;
import com.kite.libai.provider.community.event.model.ReplyEvent;
import com.kite.libai.provider.community.model.entity.Comment;
import com.kite.libai.provider.community.model.entity.Entry;
import com.kite.libai.provider.community.service.EntryService;
import com.kite.libai.provider.community.service.UserSocialService;
import org.springframework.stereotype.Component;

import com.google.common.eventbus.Subscribe;
import com.kite.libai.boot.event.core.BaseListener;

import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Component
@AllArgsConstructor
public class UserSocialDataChangeListener implements BaseListener {

    private final EntryService entryService;

    private final UserSocialService userSocialService;


    /**
     * 累计关注量数据
     *
     * @param followerEvent followerEvent
     */
    @Subscribe
    public void process(FollowerEvent followerEvent) {
        if (followerEvent == null) {
            return;
        }
        if (FollowerEventType.FOLLOWER.getType().equals(followerEvent.getType())) {
            userSocialService.add(followerEvent.getAccountId(), DataType.FOLLOWING);
            userSocialService.add(followerEvent.getFriendId(), DataType.FOLLOWERS);
        } else {
            userSocialService.reduce(followerEvent.getAccountId(), DataType.FOLLOWING);
            userSocialService.reduce(followerEvent.getFriendId(), DataType.FOLLOWERS);
        }
    }

    /**
     * 累计作品量数据
     *
     * @param event event
     */
    @Subscribe
    public void process(EntryEvent event) {
        if (event == null) {
            return;
        }
        if (EntryEventType.RELEASED.getType().equals(event.getType())) {
            userSocialService.add(event.getAuthorId(), DataType.ENTRIES);
        }
        if (EntryEventType.DELETED.getType().equals(event.getType())) {
            userSocialService.reduce(event.getAuthorId(), DataType.ENTRIES);
        }
    }

    /**
     * 累计评论量数据
     *
     * @param commentEvent commentEvent
     */
    @Subscribe
    public void process(CommentEvent commentEvent) {
        Comment comment = commentEvent.getComment();
        if (comment == null) {
            return;
        }
        Entry entry = entryService.getById(comment.getEntryId());
        if (entry == null) {
            return;
        }
        if (CommentEventType.CREATED.getType().equals(commentEvent.getType())) {
            userSocialService.add(entry.getAuthorId(), DataType.REPLIES);
        } else {
            userSocialService.reduce(entry.getAuthorId(), DataType.REPLIES);
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
        Entry entry = entryService.getById(replyEvent.getEntryId());
        if (entry == null) {
            return;
        }
        if (ReplyEventType.CREATED.getType().equals(replyEvent.getType())) {
            userSocialService.add(entry.getAuthorId(), DataType.REPLIES);
        } else {
            userSocialService.reduce(entry.getAuthorId(), DataType.REPLIES);
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
        Entry entry = entryService.getById(likeEvent.getEntryId());
        if (entry == null) {
            return;
        }
        if (LikeEventType.LIKED.getType().equals(likeEvent.getType())) {
            userSocialService.add(entry.getAuthorId(), DataType.LIKES);
        } else {
            userSocialService.reduce(entry.getAuthorId(), DataType.LIKES);
        }
    }

    /**
     * 累计浏览量数据
     *
     * @param browseEvent browseEvent
     */
    @Subscribe
    public void process(BrowseEvent browseEvent) {
        userSocialService.add(browseEvent.getEntryId(), DataType.BROWSES);
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
        userSocialService.add(playEvent.getEntryId(), DataType.PLAYS);
    }
}
