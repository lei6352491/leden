package com.zhouyi.business.controller;

import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import com.zhouyi.business.core.common.ReturnCode;
import io.swagger.annotations.Api;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.alibaba.fastjson.JSONObject;
import com.zhouyi.business.common.CommonConstant;
import com.zhouyi.business.core.common.Config;
import com.zhouyi.business.core.model.Response;
import com.zhouyi.business.core.utils.HttpCall;
import com.zhouyi.business.core.utils.Sign;
import com.zhouyi.business.core.vo.WXShareVo;

@Controller
@RequestMapping(value = "/api/uc/share")
@Api(hidden = true)
public class WXShareController {

	private static final Logger logger = LoggerFactory.getLogger(WXShareController.class);

	HttpCall httpCall = new HttpCall();
	
	
	@Autowired
    private Config config;
	
	private static int tokenExpires=6000;
	
    @SuppressWarnings("rawtypes")
    @RequestMapping(value = "/getWXShareSign")
	@ResponseBody
	public Response<WXShareVo> getWXShareSign(@RequestBody HashMap urlMap, HttpServletRequest request) {
		logger.debug("WXShareController.getWXShareSign() params +++++++++: {}");
		long time11=System.currentTimeMillis();
		Response<WXShareVo> resp = new Response<WXShareVo>();
		WXShareVo vo = new WXShareVo();
		String appid = config.getWxAppId();
		try {
			
			String secret = config.getWxAppSecret();
			
			String url = (String)urlMap.get("url");
			if(StringUtils.isBlank(url)){
				url = request.getHeader("Referer");
			}
			long time1=System.currentTimeMillis();
			String ticket=null;//cacheTemplate.hget("wx:data:"+appid, "ticket");
//			logger.info("getWXShareSign_1={},{},{}", appid,ticket,System.currentTimeMillis()-time1);
			if(ticket==null){
				long time2=System.currentTimeMillis();
				String accessToken = getAccessToken(appid, secret);
				logger.info("getWXShareSign_1={},{},{},{}", appid,ticket,accessToken,System.currentTimeMillis()-time1);
				Map<String, Object> ticketMap = getJsapiTickey(accessToken);
				if((Integer) ticketMap.get("errcode") == 0){//成功
					ticket = (String)ticketMap.get("ticket");
//					cacheTemplate.hset("wx:data:"+appid, "ticket", tokenExpires, ticket);
				}else{
					resp.setCode(ReturnCode.ERROR_01.getCode());
					resp.setMsg(ReturnCode.ERROR_01.getMsg());
					return resp;
				}
				logger.info("getWXShareSign_3={}", System.currentTimeMillis()-time2);
			}
			
			long time3=System.currentTimeMillis();
			Map<String, String> signature = Sign.sign(ticket, url);
			logger.info("getWXShareSign_4={}", System.currentTimeMillis()-time3);
			vo.setAppId(appid);
			vo.setNonceStr(signature.get("nonceStr"));
			vo.setSignature(signature.get("signature"));
			vo.setTimestamp(signature.get("timestamp"));
			
			resp.setData(vo);
			resp.setCode(ReturnCode.SUCCESS.getCode());
			resp.setMsg(ReturnCode.SUCCESS.getMsg());
			
		} catch (Exception e) {
			logger.error("WXShareController.getWXShareSign_error",e);
			resp.setCode(ReturnCode.ERROR_500.getCode());
			resp.setMsg(ReturnCode.ERROR_500.getMsg());
		}
		logger.info("getWXShareSign_5={}", System.currentTimeMillis()-time11);
		return resp;
	}
	
	@SuppressWarnings("unchecked")
    private String getAccessToken(String appid, String secret){
		String accessToken = CommonConstant.GET_ACCESS_TOKEN;
		String accessTokenUrl = accessToken.replace("XAPPID", appid).replace("XSECRET", secret);
		String accessTokenStr = (String)httpCall.httpGet(accessTokenUrl);
		Map<String, String> tokenMap = JSONObject.parseObject(accessTokenStr, Map.class);
		return tokenMap.get("access_token");
	}
	
	@SuppressWarnings("unchecked")
    private Map<String, Object> getJsapiTickey(String accessToken){
		String jsapiTicket = CommonConstant.GET_JSAPI_TICKET;
		String jsapiTicketUrl = jsapiTicket.replace("XACCESSTOKEN", accessToken);
		String ticketStr = (String)httpCall.httpGet(jsapiTicketUrl);
		logger.info("ticketStr:"+ticketStr);
		return JSONObject.parseObject(ticketStr, Map.class);
	}
	
}
