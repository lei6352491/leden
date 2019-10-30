package com.zhouyi.business.aop;

import java.util.Date;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;

import com.zhouyi.business.core.model.Response;
import com.zhouyi.business.core.model.TestNameInfo;
import com.zhouyi.business.core.service.TestNameInfoService;
import com.zhouyi.business.core.vo.MemberVo;

@Component
@Aspect
public class TestNameAspect {
	private static final Logger logger = LoggerFactory.getLogger(TestNameAspect.class);
	
	@Autowired
    private TestNameInfoService testNameInfoService;
	
	@Pointcut("execution(* com.zhouyi.business.*.MemberController.testNameExplain(..))")
    public void testNameExplain() {
    }  
	
	@Around("testNameExplain()")
	public Response<Map<String,Object>> doAround(ProceedingJoinPoint joinPoint) {
		Response<Map<String,Object>> res=new Response<Map<String,Object>>();
		Object[] objs=joinPoint.getArgs();
		Map<String,String> dto=(Map<String,String>)objs[0];
		HttpServletRequest request=(HttpServletRequest)objs[1];
		String app=request.getHeader("App");
		try{
			TestNameInfo data=new TestNameInfo();
			MemberVo user=(MemberVo)request.getAttribute("userInfo");
	        if(user!=null){
	        	data.setUserId(user.getId());
	        }else{
	        	data.setUserId(-1l);
	        }
			data.setBirthday(dto.get("birthday"));
			data.setFamilyName(dto.get("surname"));
			data.setSex(Integer.valueOf(dto.get("sex")));
			data.setCreateTime((new Date()));
			if(!StringUtils.isEmpty(dto.get("latitude"))){
				data.setLatitude(Double.valueOf(dto.get("latitude")));
			}
			if(!StringUtils.isEmpty(dto.get("longitude"))){
				data.setLongitude(Double.valueOf(dto.get("longitude")));
			}
			data.setCombine(dto.get("combine"));
			data.setHomePlace(dto.get("homePlace"));
			if(StringUtils.isEmpty(dto.get("isDouble"))){
				data.setIsDouble(0);
			}else{
				data.setIsDouble(Integer.valueOf(dto.get("isDouble")));
			}
			if(StringUtils.isEmpty(app)){
				testNameInfoService.addAsyncTestNameInfo(data);
				res=(Response<Map<String,Object>>)joinPoint.proceed();
			}else{
				Long id=testNameInfoService.addTestNameInfo(data);
				res=(Response<Map<String,Object>>)joinPoint.proceed();
				res.getData().put("id", id);
			}
		}catch(Exception e){
			logger.error("testNameExplain_err=",e);
		} catch (Throwable e) {
			e.printStackTrace();
			logger.error("testNameExplain_err_1=",e);
		}
		return res;
	}
	
}
