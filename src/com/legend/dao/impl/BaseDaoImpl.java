package com.legend.dao.impl;

import java.lang.reflect.ParameterizedType;
import java.util.List;

import javax.annotation.Resource;

import org.hibernate.HibernateException;
import org.hibernate.Query;
import org.hibernate.Session;
import org.springframework.orm.hibernate4.HibernateCallback;
import org.springframework.orm.hibernate4.HibernateTemplate;

import com.legend.dao.BaseDao;

@SuppressWarnings("unchecked")
public abstract class BaseDaoImpl<T> implements BaseDao<T> {
	
	private Class<T> clazz;
	

	public BaseDaoImpl(){
		ParameterizedType type = (ParameterizedType) this.getClass().getGenericSuperclass();
		clazz = (Class<T>) type.getActualTypeArguments()[0];
	}
	
	@Resource(name="hibernateTemplate")
	private HibernateTemplate hibernateTemplate;

	@Override
	public void saveEntity(T t) {
		hibernateTemplate.save(t);
	}

	@Override
	public void saveOrUpdateEntity(T t) {
		hibernateTemplate.saveOrUpdate(t);
	}
	
	@Override
	public void updateEntity(T t) {
		hibernateTemplate.update(t);
	}
	
	@Override
	public void deleteEntity(T t) {
		hibernateTemplate.delete(t);
	}

	@SuppressWarnings("unchecked")
	@Override
	public void batchEntityBySql(String sql, Object... objects) {
		int row = 0;
		hibernateTemplate.executeWithNativeSession(new HibernateCallback() {
			final String tempsql = sql;
			@Override
			public Object doInHibernate(Session session) throws HibernateException {
				Query query = session.createSQLQuery(tempsql);
				for(int i=0;i<objects.length;++i){
					query.setParameter(i, objects[i]);
				}
				query.executeUpdate();
				return null;
			}
		});
	}

	@Override
	public T loadEntity(Class<T> t, Integer i) {
		return (T)hibernateTemplate.load(clazz, i);
	}

	@Override
	public T getEntity(Class<T> t, Integer i) {
		return (T)hibernateTemplate.get(clazz, i);
	}

	@Override
	public List<T> findEntityByHql(String hql,Object...objects) {
		return (List<T>) hibernateTemplate.find(hql, objects);
	}

	@Override
	public List<T> findEntityByHqlForPage(T t,final int offset,final int length) {
		return (List<T>) hibernateTemplate.findByExample(t, offset, length);
	}

}
