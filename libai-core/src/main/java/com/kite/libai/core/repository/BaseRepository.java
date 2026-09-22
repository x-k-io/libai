package com.kite.libai.core.repository;


import java.io.Serializable;
import java.util.Collection;
import java.util.List;

public interface BaseRepository<T> {

    boolean insert(T entity);

    boolean insert(Collection<T> entityList);

    boolean insert(Collection<T> entityList, int batchSize);

    boolean insertOrUpdate(Collection<T> entityList);

    boolean insertOrUpdate(Collection<T> entityList, int batchSize);

    boolean deleteById(Serializable id);

    boolean deleteByIds(Collection<? extends Serializable> idList);

    boolean updateById(T entity);

    boolean updateById(Collection<T> entityList);

    boolean updateById(Collection<T> entityList, int batchSize);

    boolean insertOrUpdate(T entity);

    T getById(Serializable id);

    List<T> batchGetByIds(Collection<? extends Serializable> idList);

    List<T> batchGet();
}
