package com.kite.libai.provider.message.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum NoticeType {

    /**
     * NoticeType
     */
    LIKE("like", "点赞"),
    FORWARD("forward", "转发"),
    REPLY("reply", "评论"),
    FOLLOW("follow", "关注"),
    SYSTEM("system", "系统");

    private final String type;
    private final String desc;

    public static NoticeType getByType(String type) {
        for (NoticeType noticeType : NoticeType.values()) {
            if (noticeType.getType().equals(type)) {
                return noticeType;
            }
        }
        return null;
    }
}
