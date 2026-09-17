package com.kite.libai.boot.repository;

import org.springframework.beans.factory.annotation.Autowired;

import java.io.Serializable;
import java.util.Collection;
import java.util.List;

public class BaseService<R extends BaseRepository<T>, T> {
    @Autowired
    protected R repository;

    public boolean save(T entity) {
        return repository.save(entity);
    }

    public boolean saveBatch(Collection<T> entityList) {
        return repository.saveBatch(entityList);
    }

    public boolean saveBatch(Collection<T> entityList, int batchSize) {
        return repository.saveBatch(entityList, batchSize);
    }

    public boolean saveOrUpdateBatch(Collection<T> entityList) {
        return repository.saveOrUpdateBatch(entityList);
    }

    public boolean saveOrUpdateBatch(Collection<T> entityList, int batchSize) {
        return repository.saveOrUpdateBatch(entityList, batchSize);
    }

    public boolean deleteById(Serializable id) {
        return repository.deleteById(id);
    }

    public boolean deleteByIds(Collection<? extends Serializable> idList) {
        return repository.deleteByIds(idList);
    }

    public boolean updateById(T entity) {
        return repository.updateById(entity);
    }

    public boolean updateBatchById(Collection<T> entityList) {
        return repository.updateBatchById(entityList);
    }

    public boolean updateBatchById(Collection<T> entityList, int batchSize) {
        return repository.updateBatchById(entityList, batchSize);
    }

    public boolean saveOrUpdate(T entity) {
        return repository.saveOrUpdate(entity);
    }

    public T getById(Serializable id) {
        return repository.getById(id);
    }

    public List<T> batchGetByIds(Collection<? extends Serializable> idList) {
        return repository.batchGetByIds(idList);
    }
}
