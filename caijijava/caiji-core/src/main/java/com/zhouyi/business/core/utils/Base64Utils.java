package com.zhouyi.business.core.utils;
import org.apache.commons.codec.binary.Base64;

public class Base64Utils {
	public static String b64encode(byte[] data) {
		String b64str = new String(Base64.encodeBase64(data));
		return b64str;
	}	
	
	public static byte[] b64decode(String data) {
		byte[] decodeData = Base64.decodeBase64(data.getBytes());
		return decodeData;
	}

//	public static void main(String [] args) throws Exception {
//		System.out.println(b64encode("您好".getBytes("utf-8")));
//
//		System.out.print(new String(b64decode("5oKo5aW9")));
//	}
}
