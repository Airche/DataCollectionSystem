package com.legend.service.impl;

import java.util.List;

import com.legend.dao.BaseDao;
import com.legend.service.BaseService;

public abstract class BaseServiceImpl<T> implements BaseService<T> {

	private BaseDao<T> baseDao;
	
	//此处不进行注入，子类进行覆盖此方法，子类中进行注解注入
	public void setBaseDao(BaseDao<T> baseDao) {
		this.baseDao = baseDao;
	}

	@Override
	public void saveEntity(T t) {
		baseDao.saveEntity(t);
	}

	@Override
	public void saveOrUpdateEntity(T t) {
		baseDao.saveOrUpdateEntity(t);
	}

	@Override
	public void updateEntity(T t) {
		baseDao.updateEntity(t);
	}

	@Override
	public void deleteEntity(T t) {
		baseDao.deleteEntity(t);
	}

	@Override
	public void batchEntityBySql(String sql, Object... objects) {
		baseDao.batchEntityBySql(sql, objects);
	}

	@Override
	public T loadEntity(Class<T> t, Integer i) {
		return (T) baseDao.loadEntity(t, i);
	}

	@Override
	public T getEntity(Class<T> t, Integer i) {
		return (T) baseDao.getEntity(t, i);
	}

	@Override
	public List<T> findEntityByHql(String hql, Object... objects) {
		return baseDao.findEntityByHql(hql, objects);
	}

}
