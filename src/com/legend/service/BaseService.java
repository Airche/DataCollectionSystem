package com.legend.service;

import java.util.List;

public interface BaseService<T> {
	//写入方法
	public void saveEntity(T t);
	public void saveOrUpdateEntity(T t);
	public void updateEntity(T t);
	public void deleteEntity(T t);
	public void batchEntityBySql(String sql,Object...objects);
	//读取方法
	public	T loadEntity(Class<T> t,Integer i);
	public T getEntity(Class<T> t,Integer i);
	public List<T> findEntityByHql(String hql,Object...objects);
	
	public List<T> findAllEntities();
	public Object[] uniqueResult(String hql,Object...objects);
}
