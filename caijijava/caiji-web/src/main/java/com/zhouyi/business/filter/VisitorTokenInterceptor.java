package com.zhouyi.business.filter;

import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import com.zhouyi.business.common.LocalCacheProvider;
import com.zhouyi.business.core.common.OSSConfigure;
import com.zhouyi.business.core.utils.TokenUtil;
import com.zhouyi.business.model.LoginData;

@Component
public class VisitorTokenInterceptor extends HandlerInterceptorAdapter {
	private static final Logger logger = LoggerFactory.getLogger(VisitorTokenInterceptor.class);



	@Override
	public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex) throws Exception {
		super.afterCompletion(request, response, handler, ex);
		response.setContentType("text/json; charset=utf-8");
	}

	@Override
	public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
		try{

			String token = TokenUtil.getToken(request,true,OSSConfigure.USER_TOKEN);
			if(!StringUtils.isBlank(token)){
				Map<String,LoginData> loginMap=LocalCacheProvider.getInstance().getLoginMap();
				LoginData loginData=loginMap.get(token);
				if(loginData!=null && loginData.getMember()!=null){
					request.setAttribute("userInfo", loginData.getMember());
					return true;
				}
			}

		}catch(Exception e){
			logger.error("VisitorTokenInterceptor_err=",e);
		}
		return true;
	}
}
