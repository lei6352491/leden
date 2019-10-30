package com.zhouyi.business.aop;

import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import com.zhouyi.business.core.common.ReturnCode;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;

import com.alibaba.fastjson.JSON;
import com.zhouyi.business.core.model.Response;
import com.zhouyi.business.core.service.CardService;
import com.zhouyi.business.core.vo.MemberVo;
import com.zhouyi.business.dto.RequirementsDto;

@Component
@Aspect
public class IntelFornameAspect {
	private static final Logger logger = LoggerFactory.getLogger(IntelFornameAspect.class);
	
	@Autowired
    private CardService cardService;
	
	@Pointcut("execution(* com.zhouyi.business.*.MemberController.intelForname(..))")
    public void intelForname() {  
    }  
	
	@Around("intelForname()")
	public Response<Map<String,Object>> doAround(ProceedingJoinPoint joinPoint) {
		logger.info("IntelFornameAspect_send=");
		Response<Map<String,Object>> res=new Response<Map<String,Object>>();
		Object[] objs=joinPoint.getArgs();
		RequirementsDto dto=(RequirementsDto)objs[0];
		HttpServletRequest request=(HttpServletRequest)objs[1];
		MemberVo user=(MemberVo)request.getAttribute("userInfo");
		if(!StringUtils.isEmpty(dto.getMidFiveElements()) || !StringUtils.isEmpty(dto.getLastFiveElements())
				|| !StringUtils.isEmpty(dto.getThreeTalents())){
			
			if(user.getUserType()!=1){
				logger.info("IntelFornameAspect_result={}",JSON.toJSONString(dto));
				res.setCode(ReturnCode.ERROR_1009.getCode());
				res.setMsg(ReturnCode.ERROR_1009.getMsg());
				return res;
			}
		}
		try{
			res=(Response<Map<String,Object>>)joinPoint.proceed();
		}catch(Exception e){
			logger.error("IntelFornameAspect_err=",e);
		} catch (Throwable e) {
			e.printStackTrace();
			logger.error("IntelFornameAspect_err_1=",e);
		}
		return res;
	}
	
}
