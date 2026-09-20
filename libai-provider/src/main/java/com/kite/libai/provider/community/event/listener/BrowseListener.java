package com.kite.libai.provider.community.event.listener;

import com.kite.libai.provider.community.model.entity.Browse;
import com.kite.libai.provider.community.service.BrowseService;
import org.springframework.stereotype.Component;

import com.google.common.eventbus.Subscribe;
import com.kite.libai.core.event.core.BaseListener;
import com.kite.libai.provider.community.event.model.BrowseEvent;

import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Component
@AllArgsConstructor
public class BrowseListener implements BaseListener {

    private final BrowseService browseService;

    @Subscribe
    public void process(BrowseEvent browseEvent) {
        if (browseEvent == null) {
            return;
        }
        // 创建浏览记录
        saveBrowseRecord(browseEvent);
    }

    private void saveBrowseRecord(BrowseEvent browseEvent) {
        Browse browse = new Browse();
        browse.setAccountId(browseEvent.getAccountId());
        browse.setEntityType(browseEvent.getEntityType());
        browse.setEntityId(browseEvent.getEntityId());
        browse.setEntryId(browseEvent.getEntryId());
        browseService.save(browse);
    }
}
