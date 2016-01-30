package com.legend.action;

import java.lang.reflect.ParameterizedType;

import com.opensymphony.xwork2.ActionSupport;
import com.opensymphony.xwork2.ModelDriven;
import com.opensymphony.xwork2.Preparable;

/*
 * 抽象action，专门用于继承
 */
public abstract class BaseAction<T> extends ActionSupport implements ModelDriven<T>, Preparable {

	public T model;
	
	public BaseAction(){
		ParameterizedType type = (ParameterizedType) this.getClass().getGenericSuperclass();
		Class clazz = (Class) type.getActualTypeArguments()[0];
		try {
			model = (T) clazz.newInstance();
		} catch (InstantiationException | IllegalAccessException e) {
			e.printStackTrace();
			throw new RuntimeException(e);
		}
	}
	
	@Override
	public void prepare() throws Exception {
		// TODO Auto-generated method stub

	}

	public T getModel() {
		return model;
	}
	
}
