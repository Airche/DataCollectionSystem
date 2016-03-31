package com.legend.datasource;

import org.springframework.jdbc.datasource.lookup.AbstractRoutingDataSource;

import com.legend.model.Survey;

public class MyDataSourceRouter extends AbstractRoutingDataSource{

	//检测当前Key
	@Override
	protected Object determineCurrentLookupKey() {
		MyToken myToken = MyToken.getCurrentToken();
		if(myToken!=null){
			Survey survey = myToken.getSurvey();
			Integer surveyId = survey.getId();
			//解除绑定，这样日志的插入才到主库中
			MyToken.unbindToken();
			return surveyId%2==0 ? "even" : "odd";
		}
		return null;
	}

}
