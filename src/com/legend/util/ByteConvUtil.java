package com.legend.util;

public class ByteConvUtil {

		//字符数组转换为16进制字符串
		public static String ByteToHex(byte[] src){
			StringBuffer sb = new StringBuffer();
			for(byte b : src){
				sb.append(Integer.toHexString((b&0XF0)>>4)).append(Integer.toHexString(b&0X0F));
			}
			return sb.toString().toUpperCase();
		}
		
}
