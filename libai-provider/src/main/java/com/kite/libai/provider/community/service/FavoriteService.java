package com.kite.libai.provider.community.service;

import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

import com.baomidou.mybatisplus.core.toolkit.ObjectUtils;
import com.kite.libai.core.repository.BaseService;
import com.kite.libai.common.result.PageResult;
import com.kite.libai.provider.community.model.entity.Favorite;
import com.kite.libai.provider.community.repository.FavoriteRepository;
import org.springframework.stereotype.Service;

import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
@AllArgsConstructor
public class FavoriteService extends BaseService<FavoriteRepository, Favorite> {

    public Favorite getByAccountIdAndEntryId(Long accountId, Long entryId) {
        return this.repository.getByAccountIdAndEntryId(accountId, entryId);
    }

    public PageResult<Favorite> pageGetFavoriteByAccountId(Long accountId, int pageNum, int pageSize) {
        return this.repository.pageGetFavoriteByAccountId(accountId, pageNum, pageSize);
    }

    /**
     * 查询是否点已收藏
     *
     * @param accountId accountId
     * @param entryId   entryId
     * @return Boolean
     */
    public Boolean isFavorites(Long accountId, Long entryId) {
        Favorite favorite = this.repository.getByAccountIdAndEntryId(accountId, entryId);
        return ObjectUtils.isNotNull(favorite);
    }

    /**
     * 批量查询是否已收藏
     *
     * @param accountId accountId
     * @param ids       ids
     * @return Set<Long>
     */
    public Set<Long> favorites(Long accountId, List<Long> ids) {
        List<Favorite> favorites = this.repository.getByAccountIdAndEntryIds(accountId, ids);
        return favorites.stream().map(Favorite::getEntryId).collect(Collectors.toSet());
    }
}
