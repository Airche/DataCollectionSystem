package com.legend.util;

import java.security.NoSuchAlgorithmException;

import org.junit.Test;

public class TestDigestUtil {
	
	@Test
	public void encodeTestCase(){
		String src = "aaa";
		String des = null;
		try {
			byte[] b = (byte[]) DigestUtil.encode(src);
			System.out.println(b.length);
			des = ByteConvUtil.ByteToHex(b);
		} catch (NoSuchAlgorithmException e) {
			System.out.println("加密失败"+e);
		}
		System.out.println("加密结果=["+des+"]");
	}
	
}
