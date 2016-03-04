package com.legend.util;

public class StringDataUtil {

	public static String ArrToString(String[] value) {
		StringBuffer sb = new StringBuffer();
		if(value!=null){
			int i=0;
			for(String s : value){
				if(i==0){					
					sb.append(s);
				}else{					
					sb.append(","+s);
				}
				++i;
			}
		}
		return sb.toString();
	}

}
