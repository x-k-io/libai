package com.kite.libai.provider.system.repository;

import com.kite.libai.core.repository.BaseRepository;
import com.kite.libai.provider.system.model.entity.SensitiveWord;

import java.util.List;

public interface SensitiveWordRepository extends BaseRepository<SensitiveWord> {
    List<SensitiveWord> getByStatus(Boolean status);
}
