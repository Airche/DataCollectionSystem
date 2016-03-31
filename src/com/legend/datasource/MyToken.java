package com.legend.datasource;

import com.legend.model.Survey;


/**
 *	利用ThreadLocal技术实现token机制，目的在action和router之间实现消息传递。 
 *
 */
public class MyToken {
	
	private static ThreadLocal<MyToken> threadLocal = new ThreadLocal<MyToken>();
	
	private Survey survey;
	
	/**
	 *	将指定的令牌对象绑定到当前线程 
	 */
	public static void bindToken(MyToken token){
		threadLocal.set(token);
	}
	
	/**
	 *	解除当前线程绑定的令牌 
	 */
	public static void unbindToken(){
		threadLocal.remove();
	}
	
	/**
	 *	从当前线程获得绑定的令牌 
	 */
	public static MyToken getCurrentToken(){
		return threadLocal.get();
	}

	public Survey getSurvey() {
		return survey;
	}

	public void setSurvey(Survey survey) {
		this.survey = survey;
	}
	
	
}
