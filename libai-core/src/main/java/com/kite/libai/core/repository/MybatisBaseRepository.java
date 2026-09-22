package com.kite.libai.core.repository;

import com.baomidou.mybatisplus.core.conditions.Wrapper;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.core.toolkit.CollectionUtils;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.toolkit.SqlHelper;
import com.kite.libai.common.result.PageResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;

import java.io.Serializable;
import java.util.Collection;
import java.util.List;

public class MybatisBaseRepository<M extends BaseMapper<T>, T> implements BaseRepository<T> {

    @Autowired
    protected M baseMapper;

    @Override
    public boolean insert(T entity) {
        return SqlHelper.retBool(this.baseMapper.insert(entity));
    }

    @Override
    public boolean insert(Collection<T> entityList) {
        return SqlHelper.retBool(this.baseMapper.insert(entityList));
    }

    @Override
    public boolean insert(Collection<T> entityList, int batchSize) {
        return SqlHelper.retBool(this.baseMapper.insert(entityList, batchSize));
    }

    @Override
    public boolean insertOrUpdate(Collection<T> entityList) {
        return SqlHelper.retBool(this.baseMapper.insertOrUpdate(entityList));
    }

    @Override
    public boolean insertOrUpdate(Collection<T> entityList, int batchSize) {
        return SqlHelper.retBool(this.baseMapper.insertOrUpdate(entityList, batchSize));
    }

    @Override
    public boolean deleteById(Serializable id) {
        return SqlHelper.retBool(this.baseMapper.deleteById(id));
    }

    @Override
    public boolean deleteByIds(Collection<? extends Serializable> idList) {
        return !CollectionUtils.isEmpty(idList) && SqlHelper.retBool(this.baseMapper.deleteByIds(idList));
    }

    @Override
    public boolean updateById(T entity) {
        return SqlHelper.retBool(this.baseMapper.updateById(entity));
    }

    @Override
    public boolean updateById(Collection<T> entityList) {
        return SqlHelper.retBool(this.baseMapper.updateById(entityList));
    }

    @Override
    public boolean updateById(Collection<T> entityList, int batchSize) {
        return SqlHelper.retBool(this.baseMapper.updateById(entityList, batchSize));
    }

    @Override
    @Transactional(rollbackFor = {Exception.class})
    public boolean insertOrUpdate(T entity) {
        if (null == entity) {
            return false;
        } else {
            return this.baseMapper.insertOrUpdate(entity);
        }
    }

    @Override
    public T getById(Serializable id) {
        return this.baseMapper.selectById(id);
    }

    @Override
    public List<T> batchGetByIds(Collection<? extends Serializable> idList) {
        return this.baseMapper.selectByIds(idList);
    }

    @Override
    public List<T> batchGet() {
        return this.baseMapper.selectList(Wrappers.emptyWrapper());
    }

    public boolean update(Wrapper<T> updateWrapper) {
        return this.update(null, updateWrapper);
    }

    public boolean update(T entity, Wrapper<T> updateWrapper) {
        return SqlHelper.retBool(this.baseMapper.update(entity, updateWrapper));
    }

    public PageResult<T> pageGet(Wrapper<T> queryWrapper, int pageNum, int pageSize) {
        IPage<T> page = this.baseMapper.selectPage(new Page<>(pageNum, pageSize), queryWrapper);
        return PageResult.success(page.getRecords(), page.getPages(), page.getTotal());
    }
}
