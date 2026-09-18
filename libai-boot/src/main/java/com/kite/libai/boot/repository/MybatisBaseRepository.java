package com.kite.libai.boot.repository;

import com.baomidou.mybatisplus.core.conditions.Wrapper;
import com.baomidou.mybatisplus.core.enums.SqlMethod;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.core.metadata.TableInfo;
import com.baomidou.mybatisplus.core.metadata.TableInfoHelper;
import com.baomidou.mybatisplus.core.toolkit.Assert;
import com.baomidou.mybatisplus.core.toolkit.ClassUtils;
import com.baomidou.mybatisplus.core.toolkit.CollectionUtils;
import com.baomidou.mybatisplus.core.toolkit.ReflectionKit;
import com.baomidou.mybatisplus.core.toolkit.StringUtils;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.toolkit.SqlHelper;
import com.kite.libai.core.result.PageResult;
import org.apache.ibatis.binding.MapperMethod;
import org.apache.ibatis.logging.Log;
import org.apache.ibatis.logging.LogFactory;
import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.ResolvableType;
import org.springframework.transaction.annotation.Transactional;

import java.io.Serializable;
import java.util.Collection;
import java.util.List;
import java.util.Objects;
import java.util.function.BiFunction;
import java.util.function.BiPredicate;

public class MybatisBaseRepository<M extends BaseMapper<T>, T> implements BaseRepository<T> {

    private Log log = LogFactory.getLog(this.getClass());

    @Autowired
    protected M baseMapper;
    protected Class<T> entityClass = this.currentModelClass();
    protected Class<M> mapperClass = this.currentMapperClass();

    private String getSqlStatement(SqlMethod sqlMethod) {
        return SqlHelper.getSqlStatement(this.mapperClass, sqlMethod);
    }

    private Class<M> currentMapperClass() {
        return (Class) this.getResolvableType().as(MybatisBaseRepository.class).getGeneric(new int[]{0}).getType();
    }

    private Class<T> currentModelClass() {
        return (Class) this.getResolvableType().as(MybatisBaseRepository.class).getGeneric(new int[]{1}).getType();
    }

    private ResolvableType getResolvableType() {
        return ResolvableType.forClass(ClassUtils.getUserClass(this.getClass()));
    }

    protected <E> boolean executeBatch(Collection<E> list, int batchSize, BiFunction<SqlSession, E, Integer> consumer) {
        return SqlHelper.executeBatch(this.entityClass, this.log, list, batchSize, consumer);
    }

    protected <E> boolean executeBatch(Collection<E> list, BiFunction<SqlSession, E, Integer> consumer) {
        return this.executeBatch(list, 1000, consumer);
    }

    @Override
    public boolean save(T entity) {
        return SqlHelper.retBool(this.baseMapper.insert(entity));
    }

    @Override
    @Transactional(rollbackFor = {Exception.class})
    public boolean saveBatch(Collection<T> entityList) {
        return saveBatch(entityList, 1000);
    }

    @Override
    @Transactional(rollbackFor = {Exception.class})
    public boolean saveBatch(Collection<T> entityList, int batchSize) {
        String sqlStatement = this.getSqlStatement(SqlMethod.INSERT_ONE);
        return this.executeBatch(entityList, batchSize, (sqlSession, entity) -> {
            return sqlSession.insert(sqlStatement, entity);
        });
    }

    @Override
    @Transactional(rollbackFor = {Exception.class})
    public boolean saveOrUpdateBatch(Collection<T> entityList) {
        return saveOrUpdateBatch(entityList, 1000);
    }

    @Override
    @Transactional(rollbackFor = {Exception.class})
    public boolean saveOrUpdateBatch(Collection<T> entityList, int batchSize) {
        TableInfo tableInfo = TableInfoHelper.getTableInfo(this.entityClass);
        Assert.notNull(tableInfo, "error: can not execute. because can not find cache of TableInfo for entity!", new Object[0]);
        String keyProperty = tableInfo.getKeyProperty();
        Assert.notEmpty(keyProperty, "error: can not execute. because can not find column for id from entity!", new Object[0]);
        return SqlHelper.saveOrUpdateBatch(this.entityClass, this.mapperClass, this.log, entityList, batchSize, (sqlSession, entity) -> {
            Object idVal = ReflectionKit.getFieldValue(entity, keyProperty);
            return StringUtils.checkValNull(idVal) || CollectionUtils.isEmpty(sqlSession.selectList(this.getSqlStatement(SqlMethod.SELECT_BY_ID), entity));
        }, (sqlSession, entity) -> {
            MapperMethod.ParamMap<T> param = new MapperMethod.ParamMap<>();
            param.put("et", entity);
            return sqlSession.update(this.getSqlStatement(SqlMethod.UPDATE_BY_ID), param);
        });
    }

    @Override
    public boolean deleteById(Serializable id) {
        return SqlHelper.retBool(this.baseMapper.deleteById(id));
    }

    @Override
    public boolean deleteByIds(Collection<? extends Serializable> idList) {
        return !CollectionUtils.isEmpty(idList) && SqlHelper.retBool(this.baseMapper.deleteBatchIds(idList));
    }

    @Override
    public boolean updateById(T entity) {
        return SqlHelper.retBool(this.baseMapper.updateById(entity));
    }

    @Override
    @Transactional(rollbackFor = {Exception.class})
    public boolean updateBatchById(Collection<T> entityList) {
        return this.updateBatchById(entityList, 1000);
    }

    @Override
    @Transactional(rollbackFor = {Exception.class})
    public boolean updateBatchById(Collection<T> entityList, int batchSize) {
        String sqlStatement = this.getSqlStatement(SqlMethod.UPDATE_BY_ID);
        return this.executeBatch(entityList, batchSize, (sqlSession, entity) -> {
            MapperMethod.ParamMap<T> param = new MapperMethod.ParamMap<>();
            param.put("et", entity);
            return sqlSession.update(sqlStatement, param);
        });
    }

    @Override
    @Transactional(rollbackFor = {Exception.class})
    public boolean saveOrUpdate(T entity) {
        if (null == entity) {
            return false;
        } else {
            TableInfo tableInfo = TableInfoHelper.getTableInfo(this.entityClass);
            Assert.notNull(tableInfo, "error: can not execute. because can not find cache of TableInfo for entity!", new Object[0]);
            String keyProperty = tableInfo.getKeyProperty();
            Assert.notEmpty(keyProperty, "error: can not execute. because can not find column for id from entity!", new Object[0]);
            Object idVal = ReflectionKit.getFieldValue(entity, tableInfo.getKeyProperty());
            return !StringUtils.checkValNull(idVal) && !Objects.isNull(this.getById((Serializable) idVal)) ? this.updateById(entity) : this.save(entity);
        }
    }

    @Override
    public T getById(Serializable id) {
        return this.baseMapper.selectById(id);
    }

    @Override
    public List<T> batchGetByIds(Collection<? extends Serializable> idList) {
        return this.baseMapper.selectBatchIds(idList);
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
