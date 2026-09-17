package com.kite.libai.provider.message.handle;

import com.kite.libai.provider.message.model.entity.Notice;
import com.kite.libai.provider.message.model.response.NoticeResponse;

public interface NoticeHandle {
    /**
     * 获取通知类型
     *
     * @return
     */
    String getType();

    /**
     * 通知处理
     *
     * @param notice
     * @return
     */
    NoticeResponse handle(Notice notice);
}
