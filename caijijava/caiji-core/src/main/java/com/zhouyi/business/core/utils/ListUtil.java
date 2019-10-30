package com.zhouyi.business.core.utils;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.List;

public class ListUtil {
	@SuppressWarnings("unchecked")
	public static <T> List<T> deepCopy(List<T> src) {
		try{
		    ByteArrayOutputStream byteOut = new ByteArrayOutputStream();  
		    ObjectOutputStream out = new ObjectOutputStream(byteOut);  
		    out.writeObject(src);  
		  
		    ByteArrayInputStream byteIn = new ByteArrayInputStream(byteOut.toByteArray());  
		    ObjectInputStream in = new ObjectInputStream(byteIn);  
		    List<T> dest = (List<T>) in.readObject();  
		    return dest;
		}catch(Exception e){
			e.printStackTrace();
		}
		return null;
	}  
}
