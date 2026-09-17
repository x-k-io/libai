package com.kite.libai.provider.account.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum LoginChannel {
    APP("app", 60 * 60 * 24 * 30 * 3L, Boolean.TRUE),
    WEB("web", 60 * 60 * 12L, Boolean.FALSE),
    MINI("mini", 60 * 60 * 24 * 30 * 3L, Boolean.FALSE);

    private final String channel;
    private final Long expiresSeconds;
    private final boolean needExclusive;

    public static LoginChannel getByChannel(String channel) {
        for (LoginChannel loginChannel : LoginChannel.values()) {
            if (loginChannel.channel.equals(channel)) {
                return loginChannel;
            }
        }
        return null;
    }
}
