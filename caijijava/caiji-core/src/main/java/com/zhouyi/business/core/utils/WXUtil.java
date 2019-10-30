package com.zhouyi.business.core.utils;

import java.util.HashMap;
import java.util.Map;

import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.vdurmont.emoji.EmojiParser;
import com.zhouyi.business.core.common.WeChatUserInfo;

/**
 * 微信工具类
 * 
 *
 */
public class WXUtil {
	
	private static final Logger logger = LoggerFactory.getLogger(WXUtil.class);
	
	
	//TODO 微信支付使用，后期修改使用配置项
	public final static String WECHAT_CIFMEDIA_APP_ID = "wx7929fd8de8fdaf7d";//AppID(应用ID)
	public final static String WECHAT_CIFMEDIA_APP_SECRET = "028f704469d661e14fc92e72bb8f1827";//AppSecret(应用密钥)
	public final static String WECHAT_APP_COMMERCIAL_NO = "1386074102";
	public final static String WECHAT_APP_COMMERCIAL_SECRET = "8eb7b9c9428de8e7d4a9e2979ea44162";
	
	
	public static final String WECHAT_TOKEN_URL = "https://api.weixin.qq.com/sns/oauth2/access_token?appid=APPID&secret=SECRET&code=CODE&grant_type=authorization_code";
	//获取用户信息
	public static final String WECHAT_USER_INFO_URL = "https://api.weixin.qq.com/sns/userinfo?access_token=ACCESS_TOKEN&openid=OPENID&lang=zh_CN";
	
	public static final String WECHAT_USER_DETAIL_URL = "https://api.weixin.qq.com/cgi-bin/user/info?access_token=ACCESS_TOKEN&openid=OPENID&lang=zh_CN";
	
	public static final String WECHAT_USER_ATTENTION_URL = "https://api.weixin.qq.com/cgi-bin/user/get?access_token=ACCESS_TOKEN";

	public static final String WECHAT_CGI_TOKEN_URL ="https://api.weixin.qq.com/cgi-bin/token?grant_type=client_credential&appid=APPID&secret=SECRET";

	public static final String GET_ACCESS_TOKEN = "https://api.weixin.qq.com/cgi-bin/token?grant_type=client_credential&appid=XAPPID&secret=XSECRET";
	
	
	
	/**
	 * 用CODE换ACCESS_TOKEN，从而获取openId
	 */
	public static String getOpenId(String code, String appId, String appSecret){
		String tokenUrl = WECHAT_TOKEN_URL;
		String url = tokenUrl.replace("APPID", appId).replace("SECRET", appSecret).replace("CODE", code);
		String obj = (String)ClientSSL.httpClientPost(url);
		JSONObject resultJsonObject = JSONObject.parseObject(obj);
		String openId = resultJsonObject.getString("openid");
		
		if(StringUtils.isBlank(openId)){
			logger.info("getOpenId error... {}",obj);
		}
		
		return openId;
	}
	
	/**
	 * 显式授权获取用CODE换ACCESS_TOKEN
	 * @param code
	 * @param appId
	 * @param appSecret
	 * @return
	 */
	public static WeChatUserInfo getWeChatInfo(String code, String appId, String appSecret){
		logger.info("getWeChatInfo_params_1={}-{}-{}",code,appId,appSecret);
		String tokenUrl = WECHAT_TOKEN_URL;
		String url = tokenUrl.replace("APPID", appId).replace("SECRET", appSecret).replace("CODE", code);
		
		WeChatUserInfo info = null;
		String obj =null;
		try {
			obj = (String)ClientSSL.httpClientPost(url);
			logger.info("getWeChatInfo_params_2={}",obj);
			info = JSON.parseObject(obj, WeChatUserInfo.class);
			String openId=info.getOpenid();
			String accessToken=info.getAccess_token();
			logger.info("getWeChatInfo_params_3={}",JSON.toJSONString(info));
			if(info != null && StringUtils.isNotBlank(openId) && StringUtils.isNotBlank(accessToken)){
				url = WECHAT_USER_INFO_URL.replace("ACCESS_TOKEN", info.getAccess_token()).replace("OPENID", info.getOpenid());
				obj = (String)ClientSSL.httpClientPost(url);
				logger.info("getWeChatInfo_params_4={}-{}",obj,obj.equals(""));
//				System.out.println("****************"+obj);
				Map<String,Object> userMap=JSON.parseObject(obj);
				if(userMap.get("errcode")==null){
					info = JSON.parseObject(obj, WeChatUserInfo.class);
					if(info.getNickname()!=null){
						String nickname=EmojiParser.removeAllEmojis(info.getNickname());
						info.setNickname(nickname);
					}
				}

			}
			
		} catch (Exception e) {
			logger.error("getWeChatInfo_fail={}-{}",obj,e);
			info = null;
		}
		logger.info("getWeChatInfo_success={}",JSON.toJSONString(info));
		return info;
	}
	
	public static WeChatUserInfo getWeChatDetail(String openId,String appId, String appSecret){
		logger.info("getWeChatDetail_params_1={}-{}",appId,appSecret);

		WeChatUserInfo info = null;
		String obj =null;
		try {
			String newUrl = WECHAT_CGI_TOKEN_URL.replace("APPID", appId).replace("SECRET", appSecret);
			obj = (String)ClientSSL.httpClientPost(newUrl);
			info = JSON.parseObject(obj, WeChatUserInfo.class);
			String accessToken=info.getAccess_token();
			if(info != null && StringUtils.isNotBlank(accessToken)){
				String url = WECHAT_USER_DETAIL_URL.replace("ACCESS_TOKEN", accessToken).replace("OPENID", openId);
				obj = (String)ClientSSL.httpClientPost(url);
				logger.info("getWeChatDetail_params_4={}-{}",obj,obj.equals(""));
				Map<String,Object> userMap=JSON.parseObject(obj);
				if(userMap.get("errcode")==null){
					info = JSON.parseObject(obj, WeChatUserInfo.class);
					if(info.getNickname()!=null){
						String nickname=EmojiParser.removeAllEmojis(info.getNickname());
						info.setNickname(nickname);
					}
				}
			}
			
		} catch (Exception e) {
			logger.error("getWeChatInfo_fail={}-{}",obj,e);
			info = null;
		}
		logger.info("getWeChatDetail_success={}",JSON.toJSONString(info));
		return info;
	}
	
	/**
	 * 显式授权获取用CODE换ACCESS_TOKEN
	 * @param code
	 * @param appId
	 * @param appSecret
	 * @return
	 */
	public static Map<String,Object> getAllWeChatInfo(String appId, String appSecret,String nextOpenId){
		logger.info("getAllWeChatInfo_params_1={}-{}",appId,appSecret);
		String url = WECHAT_CGI_TOKEN_URL.replace("APPID", appId).replace("SECRET", appSecret);
		Map<String,Object> datas=new HashMap<String,Object>();
		String obj =null;
		try {
			obj = (String)ClientSSL.httpClientPost(url);
			logger.info("getAllWeChatInfo_params={}",obj);
			Map<String,Object> tokenMap = JSON.parseObject(obj);
			String accessToken=tokenMap.get("access_token").toString();
			logger.info("getAllWeChatInfo_params_3={}",JSON.toJSONString(accessToken));
			if(StringUtils.isNotBlank(accessToken)){
				url = WECHAT_USER_ATTENTION_URL.replace("ACCESS_TOKEN", accessToken);
				if(nextOpenId!=null){
					url=url+"&next_openid="+nextOpenId;
				}
				obj = (String)ClientSSL.httpClientPost(url);
				datas=JSON.parseObject(obj);
				

			}
			
		} catch (Exception e) {
			logger.error("getWeChatInfo_fail={}-{}",obj,e);
		}
		return datas;
	}
	
	/**
	 * 获取微信token
	 * @param appid
	 * @param secret
	 * @return
	 */
	public static String getAccessToken(String appid, String secret){
		HttpCall httpCall = new HttpCall();
		String accessToken = GET_ACCESS_TOKEN;
		String accessTokenUrl = accessToken.replace("XAPPID", appid).replace("XSECRET", secret);
		String accessTokenStr = (String)httpCall.httpGet(accessTokenUrl);
		Map<String, String> tokenMap = JSONObject.parseObject(accessTokenStr, Map.class);
		return tokenMap.get("access_token");
	}
	
	public static void main(String[] args){
		WeChatUserInfo info=WXUtil.getWeChatDetail("oZMcW0ppbJYzzOeF1HDQG3HBQvJ8", "wx7abd963e43f0d6e5", "1df7f091921a3564b196003cd93a9570");
//		WeChatUserInfo wu=WXUtil.getWeChatInfo("011dU4uJ0QvPA62ZsMtJ0NANtJ0dU4uP","wx7abd963e43f0d6e5","1df7f091921a3564b196003cd93a9570");
		String str=EmojiParser.removeAllEmojis(" ");
		System.out.println("xxxxx_params="+str);
//		Map<String,Object> datas=CommonConstant.getAllWeChatInfo("wx88d9d563c795de7e","c8f41e51c442a42bf0328054dc393b10","o2p1Zw1Pr_L-6Lbv1-wGKtAmH1A4");
//		CommonConstant.getWeChatDetail("PhDB6I9qZ_br2NrovFrA","wx88d9d563c795de7e","c8f41e51c442a42bf0328054dc393b10");
		
		
	}
}
