package com.kite.libai.provider.system.repository;

import com.kite.libai.core.repository.BaseRepository;
import com.kite.libai.provider.system.model.entity.ProhibitedWord;

import java.util.List;

public interface ProhibitedWordRepository extends BaseRepository<ProhibitedWord> {
    List<ProhibitedWord> getByStatus(Boolean status);
}
