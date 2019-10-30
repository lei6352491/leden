package com.zhouyi.business.core.utils;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class DataValidUtil {

	public static boolean isPhone(String phone) {
		if(phone.length()!=11){
			return false;
		}
		String regExp = "^1[0-9]{10}";
		
		Pattern p = Pattern.compile(regExp);  
		  
		Matcher m = p.matcher(phone);
		return m.find();
	}

	public static void main(String[] str){
		String phone="18688401283";
		boolean d=isPhone(phone);
		System.out.println("d="+d);
	}
}
