package com.legend.cache;

import java.lang.reflect.Method;

import org.springframework.cache.interceptor.KeyGenerator;

import com.legend.util.StringDataUtil;

public class MyKeyGenerator implements KeyGenerator {

	@Override
	public Object generate(Object arg0, Method arg1, Object... arg2) {
		String className = arg0.getClass().getSimpleName();
		String mName = arg1.getName();
		String params = "";
		for(Object o : arg2){
			params = params + o.toString() +",";
		}
		String key = className+"."+mName+"("+params+")";
		return key;
	}

}
