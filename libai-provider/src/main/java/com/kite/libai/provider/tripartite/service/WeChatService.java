package com.kite.libai.provider.tripartite.service;

import com.kite.libai.core.cache.LibaiRedisTemplate;
import com.kite.libai.provider.account.model.entity.TripartiteApp;
import com.kite.libai.provider.tripartite.feign.WeChatRemoteService;
import com.kite.libai.provider.tripartite.feign.model.WeChatMiniProgramPage;
import com.kite.libai.provider.tripartite.feign.model.WeChatMiniProgramPages;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

import com.kite.libai.common.utils.ListUtils;
import com.kite.libai.core.cache.CacheKeys;
import com.kite.libai.provider.account.service.TripartiteAppService;
import com.kite.libai.core.properties.KiteLibaiProperties;

import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
@AllArgsConstructor
public class WeChatService {

    private final LibaiRedisTemplate libaiRedisTemplate;

    private final KiteLibaiProperties kiteFlyProperties;

    private final TripartiteAppService tripartiteAppService;

    private final WeChatRemoteService weChatRemoteService;

    public String getToken(String appCode) {
        TripartiteApp tripartiteApp = tripartiteAppService.getByAppCode(appCode);
        if (tripartiteApp == null) {
            return null;
        }
        return libaiRedisTemplate.get(CacheKeys.WE_CHAT_ACCESS_TOKEN__KEY.get(appCode),
                () -> weChatRemoteService.getAccessToken("client_credential", tripartiteApp.getAppId(),
                        tripartiteApp.getAppSecret()), String.class);
    }

    @Async
    public void submitPages(Long articleId) {
//        if (!KiteEnv.ONLINE.getName().equals(kiteProperties.getEnv())) {
//            return;
//        }
        WeChatMiniProgramPages pages = new WeChatMiniProgramPages();
        WeChatMiniProgramPage page = new WeChatMiniProgramPage();
        page.setPath("/pages/findCar/detail/index");
        page.setQuery("id=" + articleId);
        pages.setPages(ListUtils.of(page));
        String result = weChatRemoteService.submitPages(getToken(kiteFlyProperties.getAppCode()), pages);
        log.info("同步页面到微信 result:{}", result);
    }
}
