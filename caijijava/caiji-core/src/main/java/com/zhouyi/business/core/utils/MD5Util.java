package com.zhouyi.business.core.utils;

import java.io.UnsupportedEncodingException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.time.Instant;
import java.util.*;

import org.apache.commons.lang3.StringUtils;

public class MD5Util {
//	public static void main(String args[]){
//		String dd=MD5("888888");
//		System.out.println("dd="+dd);
//	}
	// 全局数组
    private final static String[] strDigits = { "0", "1", "2", "3", "4", "5",
            "6", "7", "8", "9", "a", "b", "c", "d", "e", "f" };
    
	public static String MD5(String str){
		return MD5(str,null);
	}
	/**
	 * 对字符串MD5加密，可带盐值
	 * @param str
	 * @param salt 盐值
	 * @return 
	 */
	public static String MD5(String str, String salt) {
		
		if(StringUtils.isBlank(str)){
			return null;
		}
		
		if(StringUtils.isNotBlank(salt)){
			str+=salt;
		}
		
	    try {
	        // Create MD5 Hash
	        MessageDigest digest = MessageDigest.getInstance("MD5");
	        try {
				digest.update(str.getBytes("UTF-8"));
			} catch (UnsupportedEncodingException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
	        byte messageDigest[] = digest.digest();

	        // Create Hex String
	        StringBuffer hexString = new StringBuffer();
	        for (int i = 0; i < messageDigest.length; i++) {
	            String h = Integer.toHexString(0xFF & messageDigest[i]);
	            while (h.length() < 2)
	                h = "0" + h;
	            hexString.append(h);
	        }
	        return hexString.toString();

	    } catch (NoSuchAlgorithmException e) {
	        e.printStackTrace();
	    }
	    return null;
	}
	
	/**
	 * 对所有非空的参数拼接后Md5加密
	 * @param params
	 * @param salt 盐值
	 * @return
	 */
	public static String getSign(final Map<String, String> params, String salt)
    {
        Map<String, String> map = new HashMap<String, String>();
        if (params != null)
            map.putAll(params);
        for (Map.Entry<String, String> entry : params.entrySet())
        {// 去除空值参数，不加入签名
            if (StringUtils.isEmpty(entry.getValue()))
                map.remove(entry.getKey());
        }
        map = sortMapByKey(map);// 排序
        StringBuffer signUrl = new StringBuffer();
        for (Map.Entry<String, String> entry : map.entrySet())
        {
            signUrl.append("&" + entry.getKey() + "=" + entry.getValue());
        }
        return MD5(signUrl.substring(1), salt);
    }
	
	/**
	 * 使用 Map按key进行排序
	 * 
	 * @param map
	 * @return
	 */
	public static Map<String, String> sortMapByKey(Map<String, String> map) {
		if (map == null || map.isEmpty()) {
			return null;
		}
		Map<String, String> sortMap = new TreeMap<String, String>(new MapKeyComparatorUtil());
		if (map != null)
			sortMap.putAll(map);
		return sortMap;
	}
	
	public final static String MD5(byte[] data) {
        char hexDigits[]={'0','1','2','3','4','5','6','7','8','9','a','b','c','d','e','f'};       
        try {
            byte[] btInput = data;
            // 获得MD5摘要算法的 MessageDigest 对象
            MessageDigest mdInst = MessageDigest.getInstance("MD5");
            // 使用指定的字节更新摘要
            mdInst.update(btInput);
            // 获得密文
            byte[] md = mdInst.digest();
            // 把密文转换成十六进制的字符串形式
            int j = md.length;
            char str[] = new char[j * 2];
            int k = 0;
            for (int i = 0; i < j; i++) {
                byte byte0 = md[i];
                str[k++] = hexDigits[byte0 >>> 4 & 0xf];
                str[k++] = hexDigits[byte0 & 0xf];
            }
            return new String(str);
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }
	
	public static String GetMD5Code(String strObj) {
        String resultString = null;
        try {
            resultString = new String(strObj);
            MessageDigest md = MessageDigest.getInstance("MD5");
            // md.digest() 该函数返回值为存放哈希值结果的byte数组
            resultString = byteToString(md.digest(strObj.getBytes()));
        } catch (NoSuchAlgorithmException ex) {
            ex.printStackTrace();
        }
        return resultString;
    }
	
	// 转换字节数组为16进制字串
    private static String byteToString(byte[] bByte) {
        StringBuffer sBuffer = new StringBuffer();
        for (int i = 0; i < bByte.length; i++) {
            sBuffer.append(byteToArrayString(bByte[i]));
        }
        return sBuffer.toString();
    }
    
 // 返回形式为数字跟字符串
    private static String byteToArrayString(byte bByte) {
        int iRet = bByte;
        // System.out.println("iRet="+iRet);
        if (iRet < 0) {
            iRet += 256;
        }
        int iD1 = iRet / 16;
        int iD2 = iRet % 16;
        return strDigits[iD1] + strDigits[iD2];
    }



    /**
      * @author 李秸康
      * @Description 生成随机盐值
      * @date 2019/6/24
      * @param
      * @return
     **/
    public static String generateSalt(String userAccount){
	    StringBuffer stringBuffer=new StringBuffer();
	    stringBuffer.append(System.currentTimeMillis()); //拼接上当前系统毫秒数
        stringBuffer.append(userAccount.hashCode());
	    stringBuffer.append(new Random().nextInt(10000)); //拼接上用户账号（唯一）
        return stringBuffer.toString();
    }



}


//比较器类  
class MapKeyComparatorUtil implements Comparator<String>{  
  public int compare(String str1, String str2) {  
      return str1.compareTo(str2);  
  }  
}  
