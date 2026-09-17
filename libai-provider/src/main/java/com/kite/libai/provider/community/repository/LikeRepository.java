package com.kite.libai.provider.community.repository;

import com.kite.libai.boot.repository.BaseRepository;
import com.kite.libai.provider.community.model.entity.Like;

import java.util.List;

public interface LikeRepository extends BaseRepository<Like> {

    Like getLike(Long accountId, Long entityId);

    List<Like> getLikeList(Long accountId, List<Long> entityIds);
}
