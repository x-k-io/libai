package com.kite.libai.boot.repository;


import java.io.Serializable;
import java.util.Collection;
import java.util.List;

public interface BaseRepository<T> {

    boolean save(T entity);

    boolean saveBatch(Collection<T> entityList);

    boolean saveBatch(Collection<T> entityList, int batchSize);

    boolean saveOrUpdateBatch(Collection<T> entityList);

    boolean saveOrUpdateBatch(Collection<T> entityList, int batchSize);

    boolean deleteById(Serializable id);

    boolean deleteByIds(Collection<? extends Serializable> idList);

    boolean updateById(T entity);

    boolean updateBatchById(Collection<T> entityList);

    boolean updateBatchById(Collection<T> entityList, int batchSize);

    boolean saveOrUpdate(T entity);

    T getById(Serializable id);

    List<T> batchGetByIds(Collection<? extends Serializable> idList);

    List<T> batchGet();
}
