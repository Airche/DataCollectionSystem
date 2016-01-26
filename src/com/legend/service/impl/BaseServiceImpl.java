package com.legend.service.impl;

import java.util.List;

import com.legend.dao.BaseDao;
import com.legend.service.BaseService;

@SuppressWarnings("unchecked")
public abstract class BaseServiceImpl<T> implements BaseService<T> {

	private BaseDao baseDao;
	
	public BaseDao getBaseDao() {
		return baseDao;
	}
	
	//怎么注入？？
	public void setBaseDao(BaseDao baseDao) {
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
	public T loadEntity(T t, Integer i) {
		return (T) baseDao.loadEntity(t, i);
	}

	@Override
	public T getEntity(T t, Integer i) {
		return (T) baseDao.getEntity(t, i);
	}

	@Override
	public List<T> findEntityByHql(String hql, Object... objects) {
		return baseDao.findEntityByHql(hql, objects);
	}

}
