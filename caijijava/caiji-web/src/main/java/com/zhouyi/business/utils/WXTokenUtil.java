package com.zhouyi.business.utils;

import java.util.Map;

import com.alibaba.fastjson.JSONObject;
import com.zhouyi.business.common.CommonConstant;
import com.zhouyi.business.core.utils.HttpCall;

/**
 * 微信工具类
 * 
 *
 */
public class WXTokenUtil {
	
	/**
	 * 获取微信token
	 * @param appid
	 * @param secret
	 * @return
	 */
	public static String getAccessToken(String appid, String secret){
		HttpCall httpCall = new HttpCall();
		String accessToken = CommonConstant.GET_ACCESS_TOKEN;
		String accessTokenUrl = accessToken.replace("XAPPID", appid).replace("XSECRET", secret);
		String accessTokenStr = (String)httpCall.httpGet(accessTokenUrl);
		Map<String, String> tokenMap = JSONObject.parseObject(accessTokenStr, Map.class);
		return tokenMap.get("access_token");
	}
	
}
