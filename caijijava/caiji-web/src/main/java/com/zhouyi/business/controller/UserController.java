package com.zhouyi.business.controller;

import javax.servlet.http.HttpServletRequest;

import io.swagger.annotations.Api;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.alibaba.fastjson.JSON;
import com.zhouyi.business.core.model.Response;
import com.zhouyi.business.core.service.UserInfoService;
import com.zhouyi.business.dto.UserDto;

@Controller
@RequestMapping("/api/user")
@Api(hidden = true)
public class UserController {
	private static final Logger logger = LoggerFactory.getLogger(UserController.class);
	
	@Autowired
	private UserInfoService userInfoService;
	
	/**
	 * 忘记密码
	 * @param dto
	 * @param request
	 * @return
	 */
	@RequestMapping("/admin/updatePassword")
	@ResponseBody
	public Response<Object> updatePassword(@RequestBody UserDto dto,HttpServletRequest request){
		logger.info("updatePassword_user={}-{}",JSON.toJSONString(dto));
		logger.info("session_id_1={}",request.getSession().getId());
		Response<Object> res=new Response<Object>();
		
		return res;
	}
	
	@RequestMapping("/admin/getVerifCode")
	@ResponseBody
	public Response<Object> getVerifCode(@RequestBody UserDto dto,HttpServletRequest request){
		Response<Object> res=new Response<Object>();
		logger.info("session_id_1={}",request.getSession().getId());
		
		try{
			
		}catch(Exception e){
			logger.error("getVerifCode_err=",e);
		}
		return res;
	}
}
