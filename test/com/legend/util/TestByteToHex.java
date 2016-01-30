package com.legend.util;

import org.junit.Test;

public class TestByteToHex {
	
		@Test
		public void byteToHexTestCase(){
			String s = "123";
			String x = ByteConvUtil.ByteToHex(s.getBytes());
			System.out.println(x);
		}
}
