package com.legend.util;

import java.util.Collection;

public class validateUtil {
	
	public static boolean isValid(String src){
		return !(src==null||src.trim().length()==0);
	}
	
	public static boolean isValid(Collection col){
		if(col==null||col.isEmpty()){
			return false;
		}
		return true;
	}
}
