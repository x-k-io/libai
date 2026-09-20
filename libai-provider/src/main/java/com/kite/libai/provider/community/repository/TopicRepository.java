package com.kite.libai.provider.community.repository;

import com.kite.libai.core.repository.BaseRepository;
import com.kite.libai.provider.community.model.entity.Topic;

import java.util.List;

public interface TopicRepository extends BaseRepository<Topic> {
    Topic getByName(String name);

    List<Topic> searchByName(String name);

    List<Topic> getByCategory(String category);
}
