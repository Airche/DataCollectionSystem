package com.legend.dao;

import java.util.List;

/*
 * 基础Dao接口
 */
public interface BaseDao<T> {
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
	public List<T> findEntityByHqlForPage(T t,final int offset,final int length);
	public Object[] uniqueResult(String hql,Object...objects);
}
