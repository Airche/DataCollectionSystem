package com.legend.util;

import java.util.Calendar;

public class LogUtil {

	/**
	 * 生成日志表名称:logs_2013_9
	 * offset：偏移量
	 */
	public static String generateLogTableName(int offset) {
		Calendar c = Calendar.getInstance();
		int year = c.get(Calendar.YEAR);
		int month = c.get(Calendar.MONTH) + 1 +offset;
		if(month>12){
			++year;
			month = month - 12;
		}else if(month<1){
			--year;
			month = month + 12;
		}
		return "logs_"+year+"_"+month;
	}
	
	public static void main(String[] args){
		System.out.println(generateLogTableName(3));
	}

}
