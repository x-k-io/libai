package com.kite.libai.core.repository;

import org.springframework.beans.factory.annotation.Autowired;

import java.io.Serializable;
import java.util.Collection;
import java.util.List;

public class BaseService<R extends BaseRepository<T>, T> {
    @Autowired
    protected R repository;

    public boolean insert(T entity) {
        return repository.insert(entity);
    }

    public boolean insert(Collection<T> entityList) {
        return repository.insert(entityList);
    }

    public boolean insert(Collection<T> entityList, int batchSize) {
        return repository.insert(entityList, batchSize);
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

    public boolean updateById(Collection<T> entityList) {
        return repository.updateById(entityList);
    }

    public boolean updateById(Collection<T> entityList, int batchSize) {
        return repository.updateById(entityList, batchSize);
    }

    public boolean insertOrUpdate(T entity) {
        return repository.insertOrUpdate(entity);
    }

    public T getById(Serializable id) {
        return repository.getById(id);
    }

    public List<T> batchGetByIds(Collection<? extends Serializable> idList) {
        return repository.batchGetByIds(idList);
    }
}
