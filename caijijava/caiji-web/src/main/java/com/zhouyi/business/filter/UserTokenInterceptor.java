package com.zhouyi.business.filter;

import java.util.Calendar;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.zhouyi.business.core.common.ReturnCode;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import com.alibaba.fastjson.JSON;
import com.zhouyi.business.common.LocalCacheProvider;
import com.zhouyi.business.core.common.OSSConfigure;
import com.zhouyi.business.core.model.Response;
import com.zhouyi.business.core.service.MemberService;
import com.zhouyi.business.core.utils.TokenUtil;
import com.zhouyi.business.core.vo.MemberVo;
import com.zhouyi.business.model.LoginData;

@Component
public class UserTokenInterceptor extends HandlerInterceptorAdapter{
	private static final Logger logger = LoggerFactory.getLogger(UserTokenInterceptor.class);
	
	@Autowired
	private MemberService memberService;
	
	@Override
	public void afterCompletion(HttpServletRequest request,
			HttpServletResponse response, Object handler, Exception ex)
			throws Exception {
		super.afterCompletion(request, response, handler, ex);
		response.setContentType("text/json; charset=utf-8");
	}
	@Override
	public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
		try{
			Response<Object> resp=new Response<Object>();
			String app=request.getHeader("App");
			String token =null;
			boolean isCookie=true;
			if(app!=null){
				isCookie=false;
				token=TokenUtil.getToken(request,isCookie,OSSConfigure.APP_TOKEN);
			}else{
				token = TokenUtil.getToken(request,isCookie,OSSConfigure.USER_TOKEN);
			}
			String method="";
			if(handler instanceof HandlerMethod){
				HandlerMethod h = (HandlerMethod) handler;
				method=h.getMethod().getName();
			}
			logger.info("UserTokenInterceptor_params = {}-{}-{}", method,app,isCookie,token);
			if(!StringUtils.isBlank(token)){
				Map<String,LoginData> loginMap=LocalCacheProvider.getInstance().getLoginMap();
				LoginData loginData=loginMap.get(token);
				if(loginData!=null && loginData.getMember()!=null){
					request.setAttribute("userInfo", loginData.getMember());
					return true;
				}else if(app!=null && !app.equals("")){
					MemberVo userVo=memberService.getMemberInfoByToken(token);
					if(userVo==null){
						resp.setCode(ReturnCode.ERROR_1000.getCode());
						resp.setMsg(ReturnCode.ERROR_1000.getMsg());
						response.getOutputStream().write(JSON.toJSONString(resp).getBytes("UTF-8"));
						return false;
					}
					Map<String,String> tokenMap=LocalCacheProvider.getInstance().getUserTokenMap();
					Calendar cal=Calendar.getInstance();
					cal.add(Calendar.MONTH, 1);
					LoginData ld=new LoginData();
					ld.setAccessToken(token);
					ld.setExpiresTime(cal.getTime());
					ld.setMember(userVo);
					loginMap.put(token, ld);
					tokenMap.put(userVo.getId().toString(), token);
					
					request.setAttribute("userInfo", userVo);
					return true;
				}else{
					TokenUtil.delCookie(request, response, OSSConfigure.USER_TOKEN);
					resp.setCode(ReturnCode.ERROR_1000.getCode());
					resp.setMsg(ReturnCode.ERROR_1000.getMsg());
					response.getOutputStream().write(JSON.toJSONString(resp).getBytes("UTF-8"));
					return false;
				}
			}
			resp.setCode(ReturnCode.ERROR_1000.getCode());
			resp.setMsg(ReturnCode.ERROR_1000.getMsg());
			response.getOutputStream().write(JSON.toJSONString(resp).getBytes("UTF-8"));
			return false;
			
		}catch(Exception e){
			logger.error("UserTokenInterceptor_err=",e);
		}
		return true;
	}
	
}
