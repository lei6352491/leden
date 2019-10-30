package com.zhouyi.business.aop;

import java.util.HashMap;
import java.util.Map;

import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.zhouyi.business.core.common.Config;
import com.zhouyi.business.core.model.FornameRequirements;
import com.zhouyi.business.core.service.OrderService;
import com.zhouyi.business.core.utils.HttpCall;
import com.zhouyi.business.utils.WXTokenUtil;

@Component
@Aspect
public class FornameRegisterAspect {
	private static final Logger logger = LoggerFactory.getLogger(FornameRegisterAspect.class);
	private final String TEMPLATE_001="AaNjht-oTH81mltJn2X5d5jhLa_ehPAYFXqIu3PnNP0";
	private final String TO_USER="oZMcW0uB1zNF4NvkfcHGKZXSdXsw,oZMcW0mRxUKAMRt0uTEoDafjfGkg";
	
	@Autowired
	private Config config;
	
	@Autowired
	private OrderService orderService;
	
	@Pointcut("execution (* com.zhouyi.business.*.MemberController.intelFornameRegister(..))")
	private void addRegisterInfo() {
	}

	@AfterReturning(returning="obj"	, pointcut="execution(* com.zhouyi.business.*.MemberController.intelFornameRegister(..))")
	public void doAfter(Object obj) {
		logger.info("FornameRegisterAspect_send={}",JSON.toJSONString(obj));
		if(obj!=null){
			try{
				String resp=JSON.toJSONString(obj);
				JSONObject result=JSON.parseObject(resp);
				String resultData=result.getString("data");
				if(resultData!=null){
					String[] users=TO_USER.split(",");
					for(String toUser:users){
						this.sendUserRegisterMessage(toUser,resultData);
					}
				}
			}catch(Exception e){
				logger.error("FornameRegisterAspect_err=",e);
			}
		}
	}
	
	private void sendUserRegisterMessage(String toUser,String orderNo){
		FornameRequirements fr=orderService.getFornameRequirementsByOrderNo(orderNo);
		String appointName=null;
		String limitName=fr.getLimitName();
		if(!StringUtils.isEmpty(fr.getMiddleName())){
			appointName="指定中间字："+fr.getMiddleName();
		}else if(!StringUtils.isEmpty(fr.getLastName())){
			appointName="指定尾字："+fr.getLastName();
		}
		String sex=(fr.getSex()==0?"女":"男");
		Map<String,Object> data=new HashMap<String,Object>();
		Map<String,String> first=new HashMap<String,String>();
		first.put("value", "客户登记信息了");
		Map<String,String> keyword1=new HashMap<String,String>();
		keyword1.put("value", fr.getFamilyName()+"姓"+sex+"宝宝");
		Map<String,String> keyword2=new HashMap<String,String>();
		keyword2.put("value", fr.getBirthday());
		Map<String,String> keyword3=new HashMap<String,String>();
		keyword3.put("value", orderNo);
		Map<String,String> remark=new HashMap<String,String>();
		String remarkStr="地区："+fr.getHomePlace();
		if(appointName!=null){
			remarkStr=remarkStr+";\n"+appointName;
		}
		if(limitName!=null){
			remarkStr=remarkStr+";\n忌讳字："+limitName;
		}
		remark.put("value", remarkStr);
		data.put("first", first);
		data.put("keyword1", keyword1);
		data.put("keyword2", keyword2);
		data.put("keyword3", keyword3);
		data.put("remark", remark);
		HttpCall httpCall = new HttpCall();
		String token=WXTokenUtil.getAccessToken(config.getWxAppId(), config.getWxAppSecret());
        String tmpurl = "https://api.weixin.qq.com/cgi-bin/message/template/send?access_token="+ token;
        JSONObject json = new JSONObject();
        json.put("touser", toUser);
        json.put("template_id", TEMPLATE_001);
        json.put("url", "");
        json.put("data", data);
        try{
            String result = (String)httpCall.httpPostCall(tmpurl, json.toString());
            logger.info("sendUserRemindMessage_result={}",result);
            
         }catch(Exception e){
            e.printStackTrace();
        }
	}
}
