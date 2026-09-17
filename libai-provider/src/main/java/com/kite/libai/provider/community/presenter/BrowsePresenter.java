package com.kite.libai.provider.community.presenter;

import com.kite.libai.core.result.PageResult;
import com.kite.libai.provider.community.model.entity.Browse;
import com.kite.libai.provider.community.service.BrowseService;
import org.springframework.stereotype.Component;

import com.kite.libai.core.context.RequestContextUtils;

import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Component
@AllArgsConstructor
public class BrowsePresenter {

    private final BrowseService browseService;

    /**
     * 分页查询浏览记录
     *
     * @param pageNum  pageNum
     * @param pageSize pageSize
     * @return Result<List < Browse>>
     */
    public PageResult<Browse> page(int pageNum, int pageSize) {
        return browseService.pageGetBrowseByAccountId(RequestContextUtils.getAccountId(), pageNum, pageSize);
    }
}
