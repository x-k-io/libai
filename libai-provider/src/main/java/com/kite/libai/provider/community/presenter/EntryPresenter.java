package com.kite.libai.provider.community.presenter;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.stream.Collectors;

import com.kite.libai.common.result.PageResult;
import com.kite.libai.common.utils.ListUtils;
import com.kite.libai.provider.throne.model.entity.User;
import com.kite.libai.provider.community.assemble.EntryAssemble;
import com.kite.libai.provider.community.event.producer.BrowseEventProducer;
import com.kite.libai.provider.community.model.entity.Browse;
import com.kite.libai.provider.community.model.entity.Entry;
import com.kite.libai.provider.community.model.entity.Favorite;
import com.kite.libai.provider.community.model.request.EntryQueryParam;
import com.kite.libai.provider.community.model.response.EntryResponse;
import com.kite.libai.provider.community.model.response.ReviewerResponse;
import com.kite.libai.provider.community.service.BrowseService;
import org.springframework.stereotype.Component;

import com.kite.libai.common.context.RequestContextUtils;
import com.kite.libai.common.exception.ServiceException;
import org.apache.commons.collections4.CollectionUtils;
import com.kite.libai.provider.community.enums.EntryStatus;
import com.kite.libai.provider.community.enums.OrderType;
import com.kite.libai.provider.throne.service.UserService;
import com.kite.libai.provider.community.service.EntryService;
import com.kite.libai.provider.community.service.FavoriteService;
import com.kite.libai.provider.throne.service.UserRoleService;
import com.kite.libai.core.properties.KiteLibaiProperties;

import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Component
@AllArgsConstructor
public class EntryPresenter {


    private final UserService userService;

    private final BrowseService browseService;

    private final FavoriteService favoriteService;

    private final EntryService entryService;

    private final EntryAssemble entryAssemble;

    private final KiteLibaiProperties kiteFlyProperties;

    private final UserRoleService userRoleService;

    private final BrowseEventProducer browseEventProducer;

    /**
     * 修改作品状态
     *
     * @param id     id
     * @param status status
     * @return boolean
     */
    public boolean updateStatus(Long id, String status) {
        return entryService.updateStatus(id, status);
    }

    /**
     * 查询作品详情
     *
     * @param id id
     * @return EntryResponse
     */
    public EntryResponse getEntry(Long id) {
        Entry entry = entryService.getById(id);
        if (entry == null) {
            throw new ServiceException("你查看的作品已删除");
        }
        Long accountId = RequestContextUtils.getAccountId();
        EntryResponse response = entryAssemble.toResponse(entry);
        if (accountId != null) {
            // 发送作品被浏览事件
            browseEventProducer.sendBrowseEvent(accountId, entry);
        }
        return response;
    }

    /**
     * 发现页
     */
    public PageResult<EntryResponse> explore(String orderType, int pageNum, int pageSize) {
        EntryQueryParam queryParam = new EntryQueryParam();
        queryParam.setStatusList(ListUtils.of(EntryStatus.RELEASED.getStatus()));
        queryParam.setOrderType(orderType);
        queryParam.setPageNum(pageNum);
        queryParam.setPageSize(pageSize);
        return pageGetEntries(queryParam);
    }

    /**
     * 定位页
     */
    public PageResult<EntryResponse> location(String city, String orderType, int pageNum, int pageSize) {
        EntryQueryParam queryParam = new EntryQueryParam();
        queryParam.setCity(city);
        queryParam.setStatusList(ListUtils.of(EntryStatus.RELEASED.getStatus()));
        queryParam.setOrderType(orderType);
        queryParam.setPageNum(pageNum);
        queryParam.setPageSize(pageSize);
        return pageGetEntries(queryParam);
    }

    /**
     * 圈子页
     */
    public PageResult<EntryResponse> circles(Long circleId, String orderType, int pageNum, int pageSize) {
        EntryQueryParam queryParam = new EntryQueryParam();
        queryParam.setCircleId(circleId);
        queryParam.setStatusList(ListUtils.of(EntryStatus.RELEASED.getStatus()));
        queryParam.setOrderType(orderType);
        queryParam.setPageNum(pageNum);
        queryParam.setPageSize(pageSize);
        return pageGetEntries(queryParam);
    }

    /**
     * 按照频道过滤
     */
    public PageResult<EntryResponse> channels(Long channelId, String orderType, int pageNum, int pageSize) {
        EntryQueryParam queryParam = new EntryQueryParam();
        queryParam.setChannelId(channelId);
        queryParam.setStatusList(ListUtils.of(EntryStatus.RELEASED.getStatus()));
        queryParam.setOrderType(orderType);
        queryParam.setPageNum(pageNum);
        queryParam.setPageSize(pageSize);
        return pageGetEntries(queryParam);
    }

    /**
     * 查询我发布的作品
     */
    public PageResult<EntryResponse> me(Long accountId, Integer pageNum, Integer pageSize) {
        EntryQueryParam queryParam = new EntryQueryParam();
        queryParam.setAuthorId(accountId);
        queryParam.setOrderType(OrderType.NEW.getType());
        queryParam.setPageNum(pageNum);
        queryParam.setPageSize(pageSize);
        return this.pageGetEntries(queryParam);
    }

    /**
     * 查询别人发布的作品
     */
    public PageResult<EntryResponse> their(Long accountId, Integer pageNum, Integer pageSize) {
        EntryQueryParam queryParam = new EntryQueryParam();
        queryParam.setAuthorId(accountId);
        queryParam.setStatusList(ListUtils.of(EntryStatus.RELEASED.getStatus()));
        queryParam.setOrderType(OrderType.NEW.getType());
        queryParam.setPageNum(pageNum);
        queryParam.setPageSize(pageSize);
        return this.pageGetEntries(queryParam);
    }

    public PageResult<EntryResponse> browse(Long accountId, Integer pageNum, Integer pageSize) {
        PageResult<Browse> pageResult = browseService.pageGetBrowseByAccountId(accountId, pageNum, pageSize);
        List<Browse> browses = pageResult.getRecords();
        if (CollectionUtils.isEmpty(browses)) {
            return PageResult.success();
        }
        List<Long> entryIds = browses.stream().map(Browse::getEntryId).distinct().collect(Collectors.toList());
        Map<Long, Entry> map = entryService.batchGet(entryIds);
        List<Entry> records = browses.stream().map(Browse::getEntryId).map(map::get).filter(Objects::nonNull).collect(Collectors.toList());
        List<EntryResponse> responses = entryAssemble.toResponse(records);
        return PageResult.success(responses, pageResult.getPageCount(), pageResult.getTotal());
    }

    public List<ReviewerResponse> reviewer() {
        List<Long> userIds = userRoleService.getUserByRoleId(kiteFlyProperties.getReviewerRoleId());
        List<User> users = userService.batchGetByIds(userIds);
        if (CollectionUtils.isEmpty(users)) {
            return new ArrayList<>();
        }
        return users.stream().map(x -> {
            ReviewerResponse response = new ReviewerResponse();
            response.setId(x.getId());
            response.setName(x.getName());
            return response;
        }).collect(Collectors.toList());
    }

    public PageResult<EntryResponse> favorite(Long accountId, Integer pageNum, Integer pageSize) {
        PageResult<Favorite> page = favoriteService.pageGetFavoriteByAccountId(accountId, pageNum, pageSize);
        List<Favorite> favorites = page.getRecords();
        if (CollectionUtils.isEmpty(favorites)) {
            return PageResult.success();
        }
        List<Long> entryIds = favorites.stream().map(Favorite::getEntryId).distinct().collect(Collectors.toList());
        Map<Long, Entry> map = entryService.batchGet(entryIds);
        List<Entry> records = favorites.stream().map(Favorite::getEntryId).distinct().map(map::get).filter(Objects::nonNull).collect(Collectors.toList());
        List<EntryResponse> responses = entryAssemble.toResponse(records);
        return PageResult.success(responses, page.getPageCount(), page.getTotal());
    }

    /**
     * 分页查询作品信息
     *
     * @param queryParam 查询条件
     * @return Result<List < EntryResponse>>
     */
    private PageResult<EntryResponse> pageGetEntries(EntryQueryParam queryParam) {

        PageResult<Entry> page = entryService.pageGetEntries(queryParam);
        List<Entry> records = page.getRecords();
        List<EntryResponse> responses = entryAssemble.toResponse(records);
        return PageResult.success(responses, page.getPageCount(), page.getTotal());
    }
}
