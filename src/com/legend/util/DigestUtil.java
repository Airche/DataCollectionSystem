package com.legend.util;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

public class DigestUtil {
	
	public static byte[] encode(String src) throws NoSuchAlgorithmException {
		MessageDigest md = MessageDigest.getInstance("MD5");
		return md.digest(src.getBytes());
	}
}
